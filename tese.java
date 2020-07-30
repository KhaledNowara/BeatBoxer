package SoundPlayer;
import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.ShortMessage;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.*;

import SoundPlayer.MiniMiniMusicApp.MyDrawPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
public class tese extends JFrame {
	static String[] instrumentNames = {"Bass Drum\n", "Closed Hi-Hat\n",
			"Open Hi-Hat\n","Acoustic Snare\n", "Crash Cymbal\n", "Hand Clap\n",
			"High Tom\n", "Hi Bongo\n", "Maracas\n", "Whistle\n", "Low Conga\n",
			"Cowbell\n", "Vibraslap\n", "Low-mid Tom\n", "High Agogo\n",
			"Open Hi Conga\n"};
	static int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};
	private ArrayList <JCheckBox> list;
	private JPanel contentPane;
	private JPanel panel; 
	private JButton Start; 
	private JButton Stop;
	private JButton Load;
	private Music music = new Music();
	private JLabel lblNewLabel;
	private Box verticalBox;
	private MyDrawPanel ml ;
	private JSlider slider ;
	JButton btnNewButton;
	private JLabel lblNewLabel_1;
	private JButton save;
	

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tese frame = new tese();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	
	public tese() {
		
		stuff();
		EventHandling();
	}
	public void stuff () {
		setTitle("BeatBoxer");
		setIconImage(Toolkit.getDefaultToolkit().getImage(tese.class.getResource("/SoundPlayer/icons8-portable-speaker-50.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 631);
		contentPane = new JPanel();contentPane.setBackground(SystemColor.activeCaption);
		
		panel = new JPanel();panel.setBackground(SystemColor.info);
		
		Start = new JButton ("Play");
		Start.setFont(new Font("Kristen ITC", Font.PLAIN, 13));
		Stop = new JButton("Stop");
		Stop.setFont(new Font("Kristen ITC", Font.PLAIN, 13));
		btnNewButton = new JButton("Reset");
		btnNewButton.setFont(new Font("Kristen ITC", Font.PLAIN, 13));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//JList txtrbassDrumclosed = new JList(instrumentNames);
		//txtrbassDrumclosed.setBackground(SystemColor.inactiveCaption);
		//txtrbassDrumclosed.setFont(new Font("Yu Gothic UI", Font.PLAIN, 17));
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(tese.class.getResource("/SoundPlayer/icons8-drum-set-100.png")));
		
		verticalBox = Box.createVerticalBox();
		for (String i : instrumentNames) {
			JLabel j = new JLabel(i);
			j.setFont(new Font("Kristen ITC", Font.PLAIN, 19));
			verticalBox.add(j);
		}
		
	    slider = new JSlider();
	    slider.setInverted(false);
	    slider.setOrientation(SwingConstants.VERTICAL);
		Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
	    table.put(0, new JLabel("0.00 x"));
	    table.put(25, new JLabel("0.25 x"));
	    table.put(50, new JLabel("0.50 x"));
	    table.put(75, new JLabel("0.75 x "));
	    table.put(100, new JLabel("1.00 x"));
	    table.put(125, new JLabel("1.25 x"));
	    table.put(150, new JLabel("1.50 x"));
	    table.put(175, new JLabel("1.75 x"));
	    table.put(200, new JLabel("2.00 x"));
	    slider.setLabelTable(table);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(25);
		slider.setValue(100);
		slider.setMaximum(200);
		slider.setName("Tempo");
		
		lblNewLabel_1 = new JLabel("Adjust Tempo");
		lblNewLabel_1.setFont(new Font("Kristen ITC", Font.PLAIN, 13));
		
		save = new JButton("Save");
		save.setFont(new Font("Kristen ITC", Font.PLAIN, 13));
		Load = new JButton("Load");
		Load.setFont(new Font("Kristen ITC", Font.PLAIN, 13));

		
		
	
		
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(verticalBox, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE)
					.addGap(66)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(Start)
							.addGap(18)
							.addComponent(save))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(Stop)
								.addGap(18)
								.addComponent(Load))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(slider, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addGap(32)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton)
						.addComponent(lblNewLabel_1))
					.addContainerGap(39, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(Start)
									.addComponent(save))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(Stop)
										.addComponent(Load))
								.addGap(18)
								.addComponent(btnNewButton)
								.addGap(22)
								.addComponent(lblNewLabel_1)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(slider, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)))
						.addGroup(Alignment.LEADING, gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(verticalBox, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)))
					.addGap(109))
		);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		list = new ArrayList <JCheckBox>();
		for (int i = 0; i<256;i++) {
			JCheckBox Check = new JCheckBox ();
			panel.add(Check);
			list.add(Check);
			
		}
		
		contentPane.setLayout(gl_contentPane);
		
		
	}
	public void EventHandling () {
		Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean con = false;
				for (JCheckBox S:list) {
					if (S.isSelected())
						con = true;
				}
				if (con) {
				music.makeTrack(instruments,list);
				Start.setText("Playing...");
			
					
				}}
		});
		Stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				music.sequencer.stop();

				Start.setText("Play"); }});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (JCheckBox i :list) {
					i.setSelected(false);
					music.sequencer.setTempoFactor(1);
					slider.setValue(100);
				}
				slider.setValue(100);
				music.sequencer.stop();
					Start.setText("Play");
				}});
				
		slider.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent arg0) {
		    	float factor = ((float)slider.getValue()/(float)100);
		    	music.sequencer.setTempoFactor((float) (factor));}
		    });
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean [] check = new boolean[256];
				for(int i=0;i<256;i++) {
					check[i] = list.get(i).isSelected();
				}
				
				ImageIcon icon = new ImageIcon(tese.class.getResource("/SoundPlayer/icons8-brake-warning-50.png")); 
				String s = JOptionPane.showInputDialog(null, "Please name the track!",
		                "Green dinner", JOptionPane.INFORMATION_MESSAGE,icon,null," ").toString()+".ser";
				File f = new File(s);
				try {
					FileOutputStream fileStream = new FileOutputStream(f);
					ObjectOutputStream os = new ObjectOutputStream(fileStream);
					os.writeObject(check);
					JOptionPane.showMessageDialog(null, "File Saved !");
					} catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Error 101 ");
					}
			
			}
		});
		Load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean[] checkboxState = null;
				File f = new File("findDirectory.txt");
				try {
					FileOutputStream fileStream = new FileOutputStream(f);
					JFileChooser j = new JFileChooser(f);  
					j.showOpenDialog(null); 
					try {
					FileInputStream fileIn = new FileInputStream(j.getSelectedFile());
					ObjectInputStream is = new ObjectInputStream(fileIn);
					checkboxState = (boolean[]) is.readObject();
					for (int i = 0; i < 256; i++) {
						JCheckBox check = (JCheckBox) list.get(i);
						if (checkboxState[i]) {
						check.setSelected(true);
						} else {
						check.setSelected(false);
						}
					}} catch(Exception ex) {ex.printStackTrace();}
					
					
					music.sequencer.stop();
				} catch (FileNotFoundException e) {
					
					
				}
				
				
			}
		});
		Load.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent arg0) {
						Load.setToolTipText("Select a .ser File With the name of your Track");}});
				
				
		
	}
}
