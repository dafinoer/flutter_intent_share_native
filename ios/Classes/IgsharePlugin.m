#import "IgsharePlugin.h"
#if __has_include(<igshare/igshare-Swift.h>)
#import <igshare/igshare-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "igshare-Swift.h"
#endif

@implementation IgsharePlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftIgsharePlugin registerWithRegistrar:registrar];
}
@end
