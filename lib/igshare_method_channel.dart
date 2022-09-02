import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';
import 'package:igshare/plugin_exception.dart';

import 'igshare_platform_interface.dart';

/// An implementation of [IgSharePlatform] that uses method channels.
class MethodChannelIgShare extends IgsharePlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('plugin.flutter.io/ig_share_feed');

  @override
  Future<String?> getPlatformVersion() async {
    final version =
        await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  @override
  Future<void> shareToInstagramFeed(String path) async {
    final bool isPathFile = !Uri.parse(path).isAbsolute;
    if (isPathFile) {
      await methodChannel.invokeMethod<void>(
        'shareToInstagramFromPath',
        {'path_image': path},
      );
    } else {
      throw FileFormatException();
    }
  }
}
