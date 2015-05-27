package ui.component;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.apache.commons.io.FileUtils;

class FileNode {
	private String _strNodeName;
	private File _file;
	
	public FileNode(File f) {
		_strNodeName = (f == null) ? "" : f.getName();
		_file = f;
	}
	
	public String getName() {
		return _strNodeName;
	}
	
	public File getFile() {
		return _file;
	}
	
	public String toString() {
		return _strNodeName;
	}
}

public class FileTree extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private transient FileSelectionListener listener;
	
	protected JTree _jtMain;
	protected DefaultTreeModel _dtmMain;
	
	protected JButton _jbSelect = new JButton("Select");
	protected JButton _jbCancel = new JButton("Cancel");
	protected JPanel _jpSouth = new JPanel(new GridLayout(0, 3));
	protected JScrollPane _jspCenter;
	
	protected Collection<File> files;
	
	public FileTree(File f) {
		super("Select Test Cases");
		setSize(500, 500);
		
		DefaultMutableTreeNode top = getDirTree(f);
		
		_dtmMain = new DefaultTreeModel(top);
		_jtMain = new JTree(_dtmMain);
		
		_jtMain.setCellRenderer(new DefaultTreeCellRenderer());
		_jtMain.setShowsRootHandles(true);
		
		/*TreePath path = new TreePath(nodes);
		_jtMain.setSelectionPath(path);*/
		
		final CheckTreeManager checkTreeManager = new CheckTreeManager(_jtMain); 
		
		_jbCancel.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		_jbSelect.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				getFilesFromPaths(checkTreeManager.getSelectionModel().getSelectionPaths());
				fireFileSelected();
				setVisible(false);
				dispose();
			}
		});
		
		_jpSouth.setAlignmentX(RIGHT_ALIGNMENT);
		_jpSouth.add(_jbSelect);
		_jpSouth.add(_jbCancel);		
		
		_jspCenter = new JScrollPane(checkTreeManager.getTree());
		
		getContentPane().add(_jspCenter, BorderLayout.CENTER);	
		getContentPane().add(_jpSouth, BorderLayout.SOUTH);
		
		WindowListener myWindowCloser = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		
		addWindowListener(myWindowCloser);
		setVisible(true);
	}
	
	private static DefaultMutableTreeNode getDirTree(File f) {
		if (f == null) return null;
		
		
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(new FileNode(f));

		if (f.isDirectory()) {
			File[] files = f.listFiles();
			Arrays.sort(files,  new Comparator<File>() {
				@Override
				public int compare(File f1, File f2) {
					if (f1.isDirectory())
	                    return f2.isDirectory() ? f1.compareTo(f2) : -1;
	                else if (f2.isDirectory())
	                    return 1;

	                return f1.compareTo(f2);
				}				
			});
			for (File file : files) 
			{
				node.add(getDirTree(file));
			}
		}
		
		return node;
	}
	
	private void getFilesFromPaths(TreePath[] checkedPaths) {
		Collection<File> tempFiles = new ArrayList<File>();
		for (TreePath path : checkedPaths) {
			Object[] filePath = path.getPath();
			DefaultMutableTreeNode dtmNode = (DefaultMutableTreeNode) filePath[filePath.length -1];
			FileNode fileNode = (FileNode) dtmNode.getUserObject();
			File file = fileNode.getFile(); 
			if (file.isDirectory()) {
				tempFiles.addAll(FileUtils.listFiles(file, null, true));
			} else {
				tempFiles.add(file);
			}
		}
		files = tempFiles;
	}
	
	public Collection<File> getSelectedFiles() {
		return files;
	}
	
	/** Register a listener for SunEvents */
	  synchronized public void addFileSelectionListener(FileSelectionListener l) {
	    this.listener = l;
	  }  

	  /** Remove a listener for SunEvents */
	  synchronized public void removeFileSelectionListener(FileSelectionListener l) {
	    this.listener = null;
	  }

	  /** Fire a SunEvent to all registered listeners */
	  protected void fireFileSelected() {
	    // if we have no listeners, do nothing...
	    if (listener != null) {
	      // create the event object to send
	    	FileSelectionEvent event = new FileSelectionEvent(this, this.files);
	        listener.fileSelected(event);
	    }
	  }
	
	public static void main(String[] args) {
		new FileTree(new File("./SampleTests"));
	}

}
