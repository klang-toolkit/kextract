#define NS_ENUM(_type, _name) \
    enum _name : _type _name; enum _name : _type
#define NS_OPTIONS(_type, _name) \
    enum __attribute__((flag_enum)) _name : _type _name; \
    enum __attribute__((flag_enum)) _name : _type

typedef signed char BOOL;
typedef long NSInteger;
typedef unsigned long NSUInteger;

typedef struct _NSPoint {
    double x;
    double y;
} NSPoint;

typedef struct _NSRect {
    NSPoint origin;
    NSPoint size;
} NSRect;

typedef struct _NSRange {
    NSUInteger location;
    NSUInteger length;
} NSRange;

typedef NS_ENUM(NSInteger, KxMode) {
    KxModeOne = 1
};

typedef NS_OPTIONS(NSUInteger, KxFlags) {
    KxFlagsOne = 1 << 0
};

typedef NS_ENUM(unsigned int, KxUnsignedCode) {
    KxUnsignedCodeOne = 1
};

@interface KxSemanticHost
@property KxMode mode;
@property KxFlags flags;
@property NSRange selection;
- (BOOL)acceptsMode:(KxMode)mode flags:(KxFlags)flags;
- (NSInteger)signedIndex;
- (NSUInteger)unsignedCount;
- (KxUnsignedCode)roundTripUnsignedCode:(KxUnsignedCode)code;
- (NSPoint)translatePoint:(NSPoint)point
                     rect:(NSRect)rect
                    range:(NSRange)range
             rangePointer:(NSRange *)rangePointer;
@end

@protocol KxSemanticProtocol
- (KxMode)modeForRange:(NSRange)range;
@property KxFlags flags;
@end

@interface KxSemanticHost (Geometry)
- (NSRange)offsetRange:(NSRange)range pointer:(NSRange *)pointer;
@end
