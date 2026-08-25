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

typedef NSRange *NSRangePointer;

typedef NS_ENUM(NSInteger, KxMode) {
    KxModeOne = 1
};

typedef NS_OPTIONS(NSUInteger, KxFlags) {
    KxFlagsOne = 1 << 0
};

typedef NS_ENUM(unsigned int, KxUnsignedCode) {
    KxUnsignedCodeOne = 1
};

typedef NS_ENUM(NSInteger, KxFieldMode) {
    KxFieldModeKnown = 2
};

typedef union KxPayload {
    NSInteger integer;
    double decimal;
} KxPayload;

typedef struct KxSemanticRecord {
    KxFieldMode mode;
    NSRangePointer rangePointer;
    KxPayload payload;
    unsigned char bytes[8];
} KxSemanticRecord;

typedef struct KxPaddedRecord {
    unsigned char tag;
    NSUInteger payload;
    unsigned char tail;
} KxPaddedRecord;

typedef struct KxBitfieldRecord {
    unsigned int low : 3;
    unsigned int high : 5;
    NSUInteger payload;
    unsigned char tail;
} KxBitfieldRecord;

typedef struct KxTwoDoubles {
    double first;
    double second;
} KxTwoDoubles;

typedef struct KxFourDoubles {
    double first;
    double second;
    double third;
    double fourth;
} KxFourDoubles;

NSRange NSUnionRange(NSRange lhs, NSRange rhs);
NSRange NSIntersectionRange(NSRange lhs, NSRange rhs);
NSRange KxCurrentRange(void);

@interface KxSemanticHost
@property KxMode mode;
@property KxFlags flags;
@property NSRange selection;
- (BOOL)acceptsMode:(KxMode)mode flags:(KxFlags)flags;
- (BOOL)negateByteBool:(BOOL)value;
- (NSInteger)signedIndex;
- (NSUInteger)unsignedCount;
- (KxUnsignedCode)roundTripUnsignedCode:(KxUnsignedCode)code;
- (KxSemanticRecord)roundTripRecord:(KxSemanticRecord)record;
- (KxPaddedRecord)roundTripPaddedRecord:(KxPaddedRecord)record;
- (KxBitfieldRecord)roundTripBitfieldRecord:(KxBitfieldRecord)record;
- (KxTwoDoubles)smallStructReturn;
- (KxFourDoubles)largeStructReturn;
- (NSPoint)translatePoint:(NSPoint)point
                     rect:(NSRect)rect
                    range:(NSRange)range
             rangePointer:(NSRangePointer)rangePointer;
@end

@protocol KxSemanticProtocol
- (KxMode)modeForRange:(NSRange)range;
- (NSRangePointer)pointerForRange:(NSRange)range;
@property KxFlags flags;
@end

@interface KxSemanticHost (Geometry)
- (NSRange)offsetRange:(NSRange)range pointer:(NSRangePointer)pointer;
@end
