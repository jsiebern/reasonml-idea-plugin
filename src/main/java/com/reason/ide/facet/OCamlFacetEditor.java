package com.reason.ide.facet;

import com.intellij.facet.ui.FacetEditorContext;
import com.intellij.facet.ui.FacetEditorTab;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class OCamlFacetEditor extends FacetEditorTab {

    private final FacetEditorContext m_editorContext;
    private final OCamlFacetConfiguration m_configuration;

    private JPanel f_rootPanel;
    private com.intellij.openapi.ui.TextFieldWithBrowseButton f_ocamlLocation;
    private JCheckBox f_refmtOnSave;
    private JTextField f_refmtWidth;

    OCamlFacetEditor(FacetEditorContext editorContext, OCamlFacetConfiguration configuration) {
        m_editorContext = editorContext;
        m_configuration = configuration;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "OCaml";
    }

    @NotNull
    @Override
    public JComponent createComponent() {
        OCamlFacetConfiguration state = m_configuration.getState();
        assert state != null;

        f_ocamlLocation.setText(state.location);
        f_ocamlLocation.addBrowseFolderListener("Choose OCaml home directory: ", null, m_editorContext.getProject(),
                FileChooserDescriptorFactory.createSingleFolderDescriptor());

        f_refmtOnSave.setSelected(state.refmtOnSave);
        f_refmtWidth.setText(state.refmtWidth);

        return f_rootPanel;
    }

    @Override
    public boolean isModified() {
        return !(f_ocamlLocation.getText().equals(m_configuration.location) && f_refmtOnSave.isSelected() == m_configuration.refmtOnSave && f_refmtWidth
                .getText().equals(m_configuration.refmtWidth));
    }

    @Override
    public void apply() throws ConfigurationException {
        super.apply();
        m_configuration.location = f_ocamlLocation.getText().trim();
        m_configuration.refmtOnSave = f_refmtOnSave.isSelected();
        m_configuration.refmtWidth = f_refmtWidth.getText().trim();
    }
}
