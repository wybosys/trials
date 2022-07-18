#import "OCTest.h"
#include <sstream>

@interface OCTest ()

- (void)ptest;

@end

@implementation OCTest {

}

- (void)ptest {
    ::std::cout << "test" << ::std::endl;
}

@end
