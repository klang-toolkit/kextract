// Helper de diff visuel — équivalent Roborazzi pour le Web.
//
// Compare une capture PNG à une baseline avec une tolérance en % de pixels différents.
// Si la baseline est absente, elle est créée (premier run). Le diff est écrit sur
// disque en cas d'écart pour archivage CI.
const fs = require('fs');
const path = require('path');
const { PNG } = require('pngjs');
const pixelmatch = require('pixelmatch').default || require('pixelmatch');

/**
 * Compare `actualPng` (Buffer PNG) à la baseline `baselinePath`.
 *
 * @param {Buffer} actualPng    Capture courante (PNG encodé).
 * @param {string} baselinePath Chemin de la baseline de référence.
 * @param {object} [opts]
 * @param {number} [opts.tolerance=0.02] Fraction max de pixels différents tolérée.
 * @param {string} [opts.diffPath]       Où écrire l'image de diff en cas d'écart.
 * @returns {{status:'created'|'match'|'mismatch', ratio:number, diffPixels:number, total:number}}
 */
function assertScreenshotMatches(actualPng, baselinePath, opts = {}) {
  const tolerance = opts.tolerance ?? 0.02;
  const actual = PNG.sync.read(actualPng);

  if (!fs.existsSync(baselinePath)) {
    fs.mkdirSync(path.dirname(baselinePath), { recursive: true });
    fs.writeFileSync(baselinePath, actualPng);
    return { status: 'created', ratio: 0, diffPixels: 0, total: actual.width * actual.height };
  }

  const baseline = PNG.sync.read(fs.readFileSync(baselinePath));
  if (baseline.width !== actual.width || baseline.height !== actual.height) {
    return {
      status: 'mismatch',
      ratio: 1,
      diffPixels: actual.width * actual.height,
      total: actual.width * actual.height,
      reason: `dimensions ${actual.width}x${actual.height} != baseline ${baseline.width}x${baseline.height}`,
    };
  }

  const { width, height } = actual;
  const diff = new PNG({ width, height });
  const diffPixels = pixelmatch(actual.data, baseline.data, diff.data, width, height, { threshold: 0.1 });
  const total = width * height;
  const ratio = diffPixels / total;

  if (ratio > tolerance && opts.diffPath) {
    fs.mkdirSync(path.dirname(opts.diffPath), { recursive: true });
    fs.writeFileSync(opts.diffPath, PNG.sync.write(diff));
  }

  return { status: ratio > tolerance ? 'mismatch' : 'match', ratio, diffPixels, total };
}

module.exports = { assertScreenshotMatches };
