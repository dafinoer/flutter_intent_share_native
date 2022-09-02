import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'igshare_method_channel.dart';

abstract class IgsharePlatform extends PlatformInterface {
  /// Constructs a IgsharePlatform.
  IgsharePlatform() : super(token: _token);

  static final Object _token = Object();

  static IgsharePlatform _instance = MethodChannelIgShare();

  /// The default instance of [IgSharePlatform] to use.
  ///
  /// Defaults to [MethodChannelIgShare].
  static IgsharePlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [IgSharePlatform] when
  /// they register themselves.
  static set instance(IgsharePlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }

  Future<void> shareToInstagramFeed(String path);
}
