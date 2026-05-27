#import "Greeter.h"

@implementation Greeter

+ (instancetype)create {
    return [[Greeter alloc] init];
}

- (NSString *)greetWithName:(NSString *)name {
    return [NSString stringWithFormat:@"Hello, %@!", name];
}

@end
