package com.example.keyvaluebackup;

import android.app.backup.BackupAgentHelper;
import android.app.backup.BackupDataInput;
import android.app.backup.BackupDataOutput;
import android.app.backup.FileBackupHelper;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import java.io.IOException;

/*
 * Key Value Backup agent that backups a single file.
 */
public class TestAppBackupAgent extends BackupAgentHelper {

  // File to backup
  private static final String TEST_FILE = "backup_file";
  // A key to uniquely identify the set of backup data
  static final String FILES_BACKUP_KEY = "files_key";

  public static final String TAG = "TestAppBackupAgent";

  @Override
  public void onCreate() {
    Log.d(TAG, "onCreate");
    FileBackupHelper helper = new FileBackupHelper(this,
        TEST_FILE);
    addHelper(FILES_BACKUP_KEY, helper);
  }

  @Override
  public void onBackup(ParcelFileDescriptor oldState, BackupDataOutput data,
      ParcelFileDescriptor newState) throws IOException {
    Log.d(TAG, "onBackup");
    super.onBackup(oldState, data, newState);
  }

  @Override
  public void onRestore(BackupDataInput data, int appVersionCode,
      ParcelFileDescriptor newState) throws IOException {
    Log.d(TAG, "onRestore");
    super.onRestore(data, appVersionCode, newState);
  }
}
