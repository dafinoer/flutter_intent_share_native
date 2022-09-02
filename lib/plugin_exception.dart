class FileFormatException extends Error {
  final String? message;

  FileFormatException({this.message});

  @override
  String toString() {
    if (message != null) return 'FileFormat: ${message!}';
    return 'File Format Exception';
  }
}
