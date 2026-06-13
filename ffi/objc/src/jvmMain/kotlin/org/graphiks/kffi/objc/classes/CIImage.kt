package org.graphiks.kffi.objc

import java.lang.invoke.*
import java.lang.foreign.*
import java.lang.foreign.MemoryLayout.PathElement.*

/**
 * Kotlin/JVM wrapper for Objective-C class: CIImage
 * Superclass: NSObject
 * Protocols: NSSecureCoding, NSCopying
 */
open class CIImage(override val ptr: MemorySegment) : NSObject(ptr) {
    companion object {
        private val _class: MemorySegment by lazy { ObjCRuntime.getClass("CIImage") }
        
        fun imageWithCGImage(image: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageWithCGImage:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, image) as MemorySegment
        }
        
        fun imageWithCGImage_options(image: MemorySegment, options: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageWithCGImage:options:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, image, options) as MemorySegment
        }
        
        fun imageWithCGImageSource_index_options(source: MemorySegment, index: Long, dict: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageWithCGImageSource:index:options:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, source, index, dict) as MemorySegment
        }
        
        fun imageWithCGLayer(layer: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageWithCGLayer:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, layer) as MemorySegment
        }
        
        fun imageWithCGLayer_options(layer: MemorySegment, options: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageWithCGLayer:options:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, layer, options) as MemorySegment
        }
        
        fun imageWithBitmapData_bytesPerRow_size_format_colorSpace(`data`: MemorySegment, bytesPerRow: Long, size: MemorySegment, format: Int, colorSpace: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageWithBitmapData:bytesPerRow:size:format:colorSpace:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, `data`, bytesPerRow, ObjCRuntime.ObjCStructArg(size, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")), format, colorSpace) as MemorySegment
        }
        
        fun imageWithTexture_size_flipped_colorSpace(name: Int, size: MemorySegment, flipped: Boolean, colorSpace: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageWithTexture:size:flipped:colorSpace:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name, ObjCRuntime.ObjCStructArg(size, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")), flipped, colorSpace) as MemorySegment
        }
        
        fun imageWithTexture_size_flipped_options(name: Int, size: MemorySegment, flipped: Boolean, options: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageWithTexture:size:flipped:options:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, name, ObjCRuntime.ObjCStructArg(size, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")), flipped, options) as MemorySegment
        }
        
        fun imageWithMTLTexture_options(texture: MemorySegment, options: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageWithMTLTexture:options:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, texture, options) as MemorySegment
        }
        
        fun imageWithContentsOfURL(url: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageWithContentsOfURL:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, url) as MemorySegment
        }
        
        fun imageWithContentsOfURL_options(url: MemorySegment, options: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageWithContentsOfURL:options:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, url, options) as MemorySegment
        }
        
        fun imageWithData(`data`: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageWithData:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, `data`) as MemorySegment
        }
        
        fun imageWithData_options(`data`: MemorySegment, options: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageWithData:options:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, `data`, options) as MemorySegment
        }
        
        fun imageWithCVImageBuffer(imageBuffer: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageWithCVImageBuffer:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, imageBuffer) as MemorySegment
        }
        
        fun imageWithCVImageBuffer_options(imageBuffer: MemorySegment, options: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageWithCVImageBuffer:options:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, imageBuffer, options) as MemorySegment
        }
        
        fun imageWithCVPixelBuffer(pixelBuffer: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageWithCVPixelBuffer:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, pixelBuffer) as MemorySegment
        }
        
        fun imageWithCVPixelBuffer_options(pixelBuffer: MemorySegment, options: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageWithCVPixelBuffer:options:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, pixelBuffer, options) as MemorySegment
        }
        
        fun imageWithIOSurface(surface: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageWithIOSurface:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, surface) as MemorySegment
        }
        
        fun imageWithIOSurface_options(surface: MemorySegment, options: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageWithIOSurface:options:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, surface, options) as MemorySegment
        }
        
        fun imageWithColor(color: MemorySegment): MemorySegment {
            val sel = ObjCRuntime.sel("imageWithColor:")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel, color) as MemorySegment
        }
        
        fun emptyImage(): MemorySegment {
            val sel = ObjCRuntime.sel("emptyImage")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun blackImage(): MemorySegment {
            val sel = ObjCRuntime.sel("blackImage")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun whiteImage(): MemorySegment {
            val sel = ObjCRuntime.sel("whiteImage")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun grayImage(): MemorySegment {
            val sel = ObjCRuntime.sel("grayImage")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun redImage(): MemorySegment {
            val sel = ObjCRuntime.sel("redImage")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun greenImage(): MemorySegment {
            val sel = ObjCRuntime.sel("greenImage")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun blueImage(): MemorySegment {
            val sel = ObjCRuntime.sel("blueImage")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun cyanImage(): MemorySegment {
            val sel = ObjCRuntime.sel("cyanImage")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun magentaImage(): MemorySegment {
            val sel = ObjCRuntime.sel("magentaImage")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun yellowImage(): MemorySegment {
            val sel = ObjCRuntime.sel("yellowImage")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
        fun clearImage(): MemorySegment {
            val sel = ObjCRuntime.sel("clearImage")
            return ObjCRuntime.msgSend(ValueLayout.ADDRESS, _class, sel) as MemorySegment
        }
        
    }
    
    open fun initWithCGImage(image: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCGImage:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, image) as MemorySegment
    }
    
    open fun initWithCGImage_options(image: MemorySegment, options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCGImage:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, image, options) as MemorySegment
    }
    
    open fun initWithCGImageSource_index_options(source: MemorySegment, index: Long, dict: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCGImageSource:index:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, source, index, dict) as MemorySegment
    }
    
    open fun initWithCGLayer(layer: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCGLayer:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, layer) as MemorySegment
    }
    
    open fun initWithCGLayer_options(layer: MemorySegment, options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCGLayer:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, layer, options) as MemorySegment
    }
    
    open fun initWithData(`data`: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithData:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, `data`) as MemorySegment
    }
    
    open fun initWithData_options(`data`: MemorySegment, options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithData:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, `data`, options) as MemorySegment
    }
    
    open fun initWithBitmapData_bytesPerRow_size_format_colorSpace(`data`: MemorySegment, bytesPerRow: Long, size: MemorySegment, format: Int, colorSpace: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithBitmapData:bytesPerRow:size:format:colorSpace:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, `data`, bytesPerRow, ObjCRuntime.ObjCStructArg(size, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")), format, colorSpace) as MemorySegment
    }
    
    open fun initWithTexture_size_flipped_colorSpace(name: Int, size: MemorySegment, flipped: Boolean, colorSpace: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithTexture:size:flipped:colorSpace:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, name, ObjCRuntime.ObjCStructArg(size, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")), flipped, colorSpace) as MemorySegment
    }
    
    open fun initWithTexture_size_flipped_options(name: Int, size: MemorySegment, flipped: Boolean, options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithTexture:size:flipped:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, name, ObjCRuntime.ObjCStructArg(size, MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("CGSize")), flipped, options) as MemorySegment
    }
    
    open fun initWithMTLTexture_options(texture: MemorySegment, options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithMTLTexture:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, texture, options) as MemorySegment
    }
    
    open fun initWithContentsOfURL(url: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithContentsOfURL:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, url) as MemorySegment
    }
    
    open fun initWithContentsOfURL_options(url: MemorySegment, options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithContentsOfURL:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, url, options) as MemorySegment
    }
    
    open fun initWithIOSurface(surface: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithIOSurface:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, surface) as MemorySegment
    }
    
    open fun initWithIOSurface_options(surface: MemorySegment, options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithIOSurface:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, surface, options) as MemorySegment
    }
    
    open fun initWithIOSurface_plane_format_options(surface: MemorySegment, plane: Long, format: Int, options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithIOSurface:plane:format:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, surface, plane, format, options) as MemorySegment
    }
    
    open fun initWithCVImageBuffer(imageBuffer: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCVImageBuffer:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, imageBuffer) as MemorySegment
    }
    
    open fun initWithCVImageBuffer_options(imageBuffer: MemorySegment, options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCVImageBuffer:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, imageBuffer, options) as MemorySegment
    }
    
    open fun initWithCVPixelBuffer(pixelBuffer: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCVPixelBuffer:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, pixelBuffer) as MemorySegment
    }
    
    open fun initWithCVPixelBuffer_options(pixelBuffer: MemorySegment, options: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithCVPixelBuffer:options:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, pixelBuffer, options) as MemorySegment
    }
    
    open fun initWithColor(color: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("initWithColor:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, color) as MemorySegment
    }
    
    open fun imageByApplyingTransform(matrix: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("imageByApplyingTransform:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, matrix) as MemorySegment
    }
    
    open fun imageByApplyingTransform_highQualityDownsample(matrix: MemorySegment, highQualityDownsample: Boolean): MemorySegment {
        val sel = ObjCRuntime.sel("imageByApplyingTransform:highQualityDownsample:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, matrix, highQualityDownsample) as MemorySegment
    }
    
    open fun imageByApplyingOrientation(orientation: Int): MemorySegment {
        val sel = ObjCRuntime.sel("imageByApplyingOrientation:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, orientation) as MemorySegment
    }
    
    open fun imageTransformForOrientation(orientation: Int): MemorySegment {
        val sel = ObjCRuntime.sel("imageTransformForOrientation:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, orientation) as MemorySegment
    }
    
    open fun imageByApplyingCGOrientation(orientation: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("imageByApplyingCGOrientation:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, orientation) as MemorySegment
    }
    
    open fun imageTransformForCGOrientation(orientation: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("imageTransformForCGOrientation:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, orientation) as MemorySegment
    }
    
    open fun imageByCompositingOverImage(dest: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("imageByCompositingOverImage:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, dest) as MemorySegment
    }
    
    open fun imageByCroppingToRect(rect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("imageByCroppingToRect:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun imageByClampingToExtent(): MemorySegment {
        val sel = ObjCRuntime.sel("imageByClampingToExtent")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun imageByClampingToRect(rect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("imageByClampingToRect:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun imageByApplyingFilter_withInputParameters(filterName: MemorySegment, params: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("imageByApplyingFilter:withInputParameters:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, filterName, params) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun imageByApplyingFilter_withInputParameters(filterName: String, params: MemorySegment): MemorySegment = imageByApplyingFilter_withInputParameters(ObjCRuntime.newNSString(Arena.global(), filterName), params)
    
    open fun imageByApplyingFilter(filterName: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("imageByApplyingFilter:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, filterName) as MemorySegment
    }
    
    /** Convenience overload — accepts Kotlin [String] for NSString parameters. */
    fun imageByApplyingFilter(filterName: String): MemorySegment = imageByApplyingFilter(ObjCRuntime.newNSString(Arena.global(), filterName))
    
    open fun imageByColorMatchingColorSpaceToWorkingSpace(colorSpace: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("imageByColorMatchingColorSpaceToWorkingSpace:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, colorSpace) as MemorySegment
    }
    
    open fun imageByColorMatchingWorkingSpaceToColorSpace(colorSpace: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("imageByColorMatchingWorkingSpaceToColorSpace:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, colorSpace) as MemorySegment
    }
    
    open fun imageByPremultiplyingAlpha(): MemorySegment {
        val sel = ObjCRuntime.sel("imageByPremultiplyingAlpha")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun imageByUnpremultiplyingAlpha(): MemorySegment {
        val sel = ObjCRuntime.sel("imageByUnpremultiplyingAlpha")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun imageBySettingAlphaOneInExtent(extent: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("imageBySettingAlphaOneInExtent:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, ObjCRuntime.ObjCStructArg(extent, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    open fun imageByApplyingGaussianBlurWithSigma(sigma: Double): MemorySegment {
        val sel = ObjCRuntime.sel("imageByApplyingGaussianBlurWithSigma:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, sigma) as MemorySegment
    }
    
    open fun imageBySettingProperties(properties: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("imageBySettingProperties:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, properties) as MemorySegment
    }
    
    open fun imageBySamplingLinear(): MemorySegment {
        val sel = ObjCRuntime.sel("imageBySamplingLinear")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun imageBySamplingNearest(): MemorySegment {
        val sel = ObjCRuntime.sel("imageBySamplingNearest")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun imageByInsertingIntermediate(): MemorySegment {
        val sel = ObjCRuntime.sel("imageByInsertingIntermediate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun imageByInsertingTiledIntermediate(): MemorySegment {
        val sel = ObjCRuntime.sel("imageByInsertingTiledIntermediate")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    open fun imageByApplyingGainMap(gainmap: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("imageByApplyingGainMap:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, gainmap) as MemorySegment
    }
    
    open fun imageByApplyingGainMap_headroom(gainmap: MemorySegment, headroom: Float): MemorySegment {
        val sel = ObjCRuntime.sel("imageByApplyingGainMap:headroom:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, gainmap, headroom) as MemorySegment
    }
    
    open fun imageBySettingContentHeadroom(headroom: Float): MemorySegment {
        val sel = ObjCRuntime.sel("imageBySettingContentHeadroom:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, headroom) as MemorySegment
    }
    
    open fun imageBySettingContentAverageLightLevel(average: Float): MemorySegment {
        val sel = ObjCRuntime.sel("imageBySettingContentAverageLightLevel:")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel, average) as MemorySegment
    }
    
    open fun regionOfInterestForImage_inRect(image: MemorySegment, rect: MemorySegment): MemorySegment {
        val sel = ObjCRuntime.sel("regionOfInterestForImage:inRect:")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel, image, ObjCRuntime.ObjCStructArg(rect, MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"))) as MemorySegment
    }
    
    // @property blackImage
    open fun blackImage(): MemorySegment {
        val sel = ObjCRuntime.sel("blackImage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property whiteImage
    open fun whiteImage(): MemorySegment {
        val sel = ObjCRuntime.sel("whiteImage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property grayImage
    open fun grayImage(): MemorySegment {
        val sel = ObjCRuntime.sel("grayImage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property redImage
    open fun redImage(): MemorySegment {
        val sel = ObjCRuntime.sel("redImage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property greenImage
    open fun greenImage(): MemorySegment {
        val sel = ObjCRuntime.sel("greenImage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property blueImage
    open fun blueImage(): MemorySegment {
        val sel = ObjCRuntime.sel("blueImage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property cyanImage
    open fun cyanImage(): MemorySegment {
        val sel = ObjCRuntime.sel("cyanImage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property magentaImage
    open fun magentaImage(): MemorySegment {
        val sel = ObjCRuntime.sel("magentaImage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property yellowImage
    open fun yellowImage(): MemorySegment {
        val sel = ObjCRuntime.sel("yellowImage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property clearImage
    open fun clearImage(): MemorySegment {
        val sel = ObjCRuntime.sel("clearImage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property extent
    open fun extent(): MemorySegment {
        val sel = ObjCRuntime.sel("extent")
        return ObjCRuntime.msgSendStret(MemoryLayout.structLayout(MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("x"), ValueLayout.JAVA_DOUBLE.withName("y")).withName("origin"), MemoryLayout.structLayout(ValueLayout.JAVA_DOUBLE.withName("width"), ValueLayout.JAVA_DOUBLE.withName("height")).withName("size")).withName("CGRect"), ptr, sel) as MemorySegment
    }
    
    // @property opaque
    open fun isOpaque(): Boolean {
        val sel = ObjCRuntime.sel("isOpaque")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_BOOLEAN, ptr, sel) as Boolean
    }
    
    // @property properties
    /** @return NSDictionary<NSString *,id> * */
    open fun properties(): MemorySegment {
        val sel = ObjCRuntime.sel("properties")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property definition
    open fun definition(): MemorySegment {
        val sel = ObjCRuntime.sel("definition")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property url
    open fun url(): MemorySegment {
        val sel = ObjCRuntime.sel("url")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property colorSpace
    open fun colorSpace(): MemorySegment {
        val sel = ObjCRuntime.sel("colorSpace")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property contentHeadroom
    open fun contentHeadroom(): Float {
        val sel = ObjCRuntime.sel("contentHeadroom")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, ptr, sel) as Float
    }
    
    // @property contentAverageLightLevel
    open fun contentAverageLightLevel(): Float {
        val sel = ObjCRuntime.sel("contentAverageLightLevel")
        return ObjCRuntime.msgSend(ValueLayout.JAVA_FLOAT, ptr, sel) as Float
    }
    
    // @property pixelBuffer
    open fun pixelBuffer(): MemorySegment {
        val sel = ObjCRuntime.sel("pixelBuffer")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property CGImage
    open fun CGImage(): MemorySegment {
        val sel = ObjCRuntime.sel("CGImage")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    // @property metalTexture
    /** @return id<MTLTexture> */
    open fun metalTexture(): MemorySegment {
        val sel = ObjCRuntime.sel("metalTexture")
        return ObjCRuntime.msgSend(ValueLayout.ADDRESS, ptr, sel) as MemorySegment
    }
    
    
    // ── Instance variables (direct field access not supported via Panama) ──
    // ivar: _state: MemorySegment
    // ivar: _priv: MemorySegment
}

// ── Category: AutoAdjustment on CIImage ─────────────────────────────────────────

/** @return NSArray<CIFilter *> * */
fun CIImage.autoAdjustmentFilters(): MemorySegment {
    val sel = ObjCRuntime.sel("autoAdjustmentFilters")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

/** @return NSArray<CIFilter *> * */
fun CIImage.autoAdjustmentFiltersWithOptions(options: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("autoAdjustmentFiltersWithOptions:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, options) as MemorySegment
}

// ── Category: LabConversion on CIImage ─────────────────────────────────────────

fun CIImage.imageByConvertingWorkingSpaceToLab(): MemorySegment {
    val sel = ObjCRuntime.sel("imageByConvertingWorkingSpaceToLab")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

fun CIImage.imageByConvertingLabToWorkingSpace(): MemorySegment {
    val sel = ObjCRuntime.sel("imageByConvertingLabToWorkingSpace")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// ── Category: AVDepthData on CIImage ─────────────────────────────────────────

fun CIImage.initWithDepthData_options(`data`: MemorySegment, options: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithDepthData:options:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, `data`, options) as MemorySegment
}

fun CIImage.initWithDepthData(`data`: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithDepthData:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, `data`) as MemorySegment
}

fun CIImage.depthData(): MemorySegment {
    val sel = ObjCRuntime.sel("depthData")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// Class method: +[CIImage imageWithDepthData:options:]
fun CIImage_imageWithDepthData_options(`data`: MemorySegment, options: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("imageWithDepthData:options:")
    val cls = ObjCRuntime.getClass("CIImage")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, `data`, options) as MemorySegment
}

// Class method: +[CIImage imageWithDepthData:]
fun CIImage_imageWithDepthData(`data`: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("imageWithDepthData:")
    val cls = ObjCRuntime.getClass("CIImage")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, `data`) as MemorySegment
}

// ── Category: AVPortraitEffectsMatte on CIImage ─────────────────────────────────────────

fun CIImage.initWithPortaitEffectsMatte_options(matte: MemorySegment, options: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithPortaitEffectsMatte:options:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, matte, options) as MemorySegment
}

fun CIImage.initWithPortaitEffectsMatte(matte: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithPortaitEffectsMatte:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, matte) as MemorySegment
}

fun CIImage.portraitEffectsMatte(): MemorySegment {
    val sel = ObjCRuntime.sel("portraitEffectsMatte")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// Class method: +[CIImage imageWithPortaitEffectsMatte:options:]
fun CIImage_imageWithPortaitEffectsMatte_options(matte: MemorySegment, options: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("imageWithPortaitEffectsMatte:options:")
    val cls = ObjCRuntime.getClass("CIImage")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, matte, options) as MemorySegment
}

// Class method: +[CIImage imageWithPortaitEffectsMatte:]
fun CIImage_imageWithPortaitEffectsMatte(matte: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("imageWithPortaitEffectsMatte:")
    val cls = ObjCRuntime.getClass("CIImage")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, matte) as MemorySegment
}

// ── Category: AVSemanticSegmentationMatte on CIImage ─────────────────────────────────────────

fun CIImage.initWithSemanticSegmentationMatte_options(matte: MemorySegment, options: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithSemanticSegmentationMatte:options:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, matte, options) as MemorySegment
}

fun CIImage.initWithSemanticSegmentationMatte(matte: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithSemanticSegmentationMatte:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, matte) as MemorySegment
}

fun CIImage.semanticSegmentationMatte(): MemorySegment {
    val sel = ObjCRuntime.sel("semanticSegmentationMatte")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel) as MemorySegment
}

// Class method: +[CIImage imageWithSemanticSegmentationMatte:options:]
fun CIImage_imageWithSemanticSegmentationMatte_options(matte: MemorySegment, options: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("imageWithSemanticSegmentationMatte:options:")
    val cls = ObjCRuntime.getClass("CIImage")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, matte, options) as MemorySegment
}

// Class method: +[CIImage imageWithSemanticSegmentationMatte:]
fun CIImage_imageWithSemanticSegmentationMatte(matte: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("imageWithSemanticSegmentationMatte:")
    val cls = ObjCRuntime.getClass("CIImage")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, cls, sel, matte) as MemorySegment
}

// ── Category: NSAppKitAdditions on CIImage ─────────────────────────────────────────

fun CIImage.initWithBitmapImageRep(bitmapImageRep: MemorySegment): MemorySegment {
    val sel = ObjCRuntime.sel("initWithBitmapImageRep:")
    return ObjCRuntime.msgSend(ValueLayout.ADDRESS, this.ptr, sel, bitmapImageRep) as MemorySegment
}

fun CIImage.drawInRect_fromRect_operation_fraction(rect: MemorySegment, fromRect: MemorySegment, op: MemorySegment, delta: Double): Unit {
    val sel = ObjCRuntime.sel("drawInRect:fromRect:operation:fraction:")
    ObjCRuntime.msgSend(null, this.ptr, sel, rect, fromRect, op, delta)
}

fun CIImage.drawAtPoint_fromRect_operation_fraction(point: MemorySegment, fromRect: MemorySegment, op: MemorySegment, delta: Double): Unit {
    val sel = ObjCRuntime.sel("drawAtPoint:fromRect:operation:fraction:")
    ObjCRuntime.msgSend(null, this.ptr, sel, point, fromRect, op, delta)
}

