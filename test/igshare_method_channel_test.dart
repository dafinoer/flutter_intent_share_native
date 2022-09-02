import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:igshare/igshare_method_channel.dart';

void main() {
  MethodChannelIgShare platform = MethodChannelIgShare();
  const MethodChannel channel = MethodChannel('plugin.flutter.io/ig_share_feed');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await platform.getPlatformVersion(), '42');
  });
}
