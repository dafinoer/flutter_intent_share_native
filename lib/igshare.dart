import 'igshare_platform_interface.dart';

class IgShare {
  Future<String?> getPlatformVersion() {
    return IgsharePlatform.instance.getPlatformVersion();
  }

  Future<void> shareToInstagram(String path) {
    return IgsharePlatform.instance.shareToInstagramFeed(path);
  }
}
