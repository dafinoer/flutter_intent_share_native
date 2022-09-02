import 'package:flutter_test/flutter_test.dart';
import 'package:igshare/igshare.dart';
import 'package:igshare/igshare_platform_interface.dart';
import 'package:igshare/igshare_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockIgSharePlatform
    with MockPlatformInterfaceMixin
    implements IgsharePlatform {
  @override
  Future<String?> getPlatformVersion() => Future.value('42');

  @override
  Future<void> shareToInstagramFeed(String path) async {
    return;
  }
}

void main() {
  final IgsharePlatform initialPlatform = IgsharePlatform.instance;

  test('$MethodChannelIgShare is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelIgShare>());
  });

  test('getPlatformVersion', () async {
    IgShare igSharePlugin = IgShare();
    MockIgSharePlatform fakePlatform = MockIgSharePlatform();
    IgsharePlatform.instance = fakePlatform;

    expect(await igSharePlugin.getPlatformVersion(), '42');
  });
}
