package com.example.keyvaluebackup;

import android.app.backup.BackupAgent;
import android.app.backup.BackupDataInput;
import android.app.backup.BackupDataOutput;
import android.app.backup.FullBackupDataOutput;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/*
 * Key Value Backup agent that backups .
 *
 * Logs callbacks into logcat.
 */
public class TestAppBackupAgent extends BackupAgent {

  public static final String TAG = "TestAppBackupAgent";

  @Override
  public void onCreate() {
    Log.d(TAG, "onCreate");
  }

  @Override
  public void onBackup(ParcelFileDescriptor oldState, BackupDataOutput data,
      ParcelFileDescriptor newState) throws IOException {
    Log.d(TAG, "Backup requested, quota is " + data.getQuota());

    // Always backup the entire file
    File testFile = new File(getFilesDir(), MainActivity.FILE_NAME);
    Log.d(TAG, "Writing " + testFile.length());

    data.writeEntityHeader(MainActivity.FILE_NAME, (int) testFile.length());
    byte[] buffer = new byte[4096];
    try (FileInputStream input = new FileInputStream(testFile)) {
      int read;
      while ((read = input.read(buffer)) >= 0) {
        data.writeEntityData(buffer, read);
      }
    }
  }

  @Override
  public void onRestore(BackupDataInput data, int appVersionCode,
      ParcelFileDescriptor newState) throws IOException {
    Log.d(MainActivity.TAG, "Restore requested");
  }

  @Override
  public void onRestoreFile(ParcelFileDescriptor data, long size,
      File destination, int type, long mode, long mtime) throws IOException {
    throw new IllegalStateException("unexpected onRestoreFile");
  }

  @Override
  public void onFullBackup(FullBackupDataOutput data) throws IOException {
    throw new IllegalStateException("unexpected onFullBackup");
  }

  @Override
  public void onQuotaExceeded(long backupDataBytes, long quotaBytes) {
    super.onQuotaExceeded(backupDataBytes, quotaBytes);
    Log.d(MainActivity.TAG, "Quota exceeded!");
  }

  @Override
  public void onRestoreFinished() {
    super.onRestoreFinished();
    Log.d(MainActivity.TAG, "onRestoreFinished");
  }

  @Override
  public void onDestroy() {
    Log.d(MainActivity.TAG, "onDestroy");
  }
}
