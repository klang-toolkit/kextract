#import <Foundation/Foundation.h>

/**
 * Simple Objective-C class used as a kextract binding example.
 */
@interface Greeter : NSObject

/** Factory — returns a new autoreleased Greeter. */
+ (instancetype)create;

/** Returns "Hello, <name>!". */
- (NSString *)greetWithName:(NSString *)name;

@end
