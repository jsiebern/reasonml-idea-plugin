package com.reason.ide;

import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.reason.ide.files.OclFileType;
import com.reason.ide.files.RmlFileType;
import com.reason.ide.hints.InferredTypesService;
import org.jetbrains.annotations.NotNull;

/**
 * Listen to editor events and query merlin for types when editor get the focus.
 */
public class RmlFileEditorListener implements FileEditorManagerListener {
    private final Project m_project;

    RmlFileEditorListener(Project project) {
        m_project = project;
    }

    @Override
    public void fileOpened(@NotNull FileEditorManager source, @NotNull VirtualFile file) { // idea#143
    }

    @Override
    public void fileClosed(@NotNull FileEditorManager source, @NotNull VirtualFile file) { // idea#143
    }

    @Override
    public void selectionChanged(@NotNull FileEditorManagerEvent event) {
        VirtualFile newFile = event.getNewFile();
        if (newFile != null) {
            FileType fileType = newFile.getFileType();
            if (fileType instanceof RmlFileType || fileType instanceof OclFileType) {
                InferredTypesService.queryForSelectedTextEditor(m_project);
            }
        }
    }
}
