package peripheral.designer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.AbstractTableModel;

import peripheral.logic.DisplayConfiguration;
import peripheral.logic.sensor.Sensor;
import peripheral.logic.sensor.SensorChannel;
import peripheral.logic.sensor.SensorServer;
import peripheral.logic.sensor.ServerConnectionStatusChangedEvent;

/**
 *
 * @author tobias
 */
public class SensorPanel extends JPanel implements Observer{

	// Components
	private javax.swing.JButton addButton;
    private javax.swing.JPanel addServerPanel;
    private javax.swing.JLabel addressLabel;
    private javax.swing.JTextField addressTextField;
    private javax.swing.JButton editButton;
    private javax.swing.JLabel httpLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel leadingLabel;
    private javax.swing.JTextField portTextField;
    private javax.swing.JButton reconnectButton;
    private javax.swing.JButton removeButton;
    private javax.swing.JLabel seperatorLabel;
    private javax.swing.JPanel serverPanel;
    private javax.swing.JTable serverTable;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextField;
	
    // Models and data structures
    private SensorTableModel tableModel;
    private List<SensorServer> serverList;
    
    // Text Strings
    private String addressTextFieldText;
    private String portTextFieldText;
    private String usernameTextFieldText;
    
    public SensorPanel() {
    	serverList = DisplayConfiguration.getInstance().getSensorServer();
    	
    	addressTextFieldText = "raab-heim.uni-linz.ac.at";
    	portTextFieldText = "8080";
    	usernameTextFieldText = "admin";
    	
        initComponents();
    }

    private void initComponents() {

        addServerPanel = new javax.swing.JPanel();
        addressTextField = new javax.swing.JTextField();
        addressLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        portTextField = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        httpLabel = new javax.swing.JLabel();
        seperatorLabel = new javax.swing.JLabel();
        serverPanel = new javax.swing.JPanel();
        leadingLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        serverTable = new javax.swing.JTable();
        editButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        reconnectButton = new javax.swing.JButton();
        tableModel = new SensorTableModel();

        addServerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Server properties"));

        addressTextField.setText(addressTextFieldText);
        addressTextField.setMinimumSize(new java.awt.Dimension(200, 28));
        addressTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressTextFieldActionPerformed(evt);
            }
        });

        addressLabel.setText("Address:");

        usernameLabel.setText("Username:");

        usernameTextField.setText(usernameTextFieldText);
        usernameTextField.setMinimumSize(new java.awt.Dimension(200, 28));
        usernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextFieldActionPerformed(evt);
            }
        });

        portTextField.setText(portTextFieldText);
        portTextField.setMaximumSize(new java.awt.Dimension(80, 28));
        portTextField.setMinimumSize(new java.awt.Dimension(80, 28));
        portTextField.setPreferredSize(new java.awt.Dimension(80, 28));
        portTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portTextFieldActionPerformed(evt);
            }
        });

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        httpLabel.setText("http://");

        seperatorLabel.setText(":");

        org.jdesktop.layout.GroupLayout addServerPanelLayout = new org.jdesktop.layout.GroupLayout(addServerPanel);
        addServerPanel.setLayout(addServerPanelLayout);
        addServerPanelLayout.setHorizontalGroup(
            addServerPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(addServerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(addServerPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(addressLabel)
                    .add(usernameLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(httpLabel)
                .add(1, 1, 1)
                .add(addServerPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(addressTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 406, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(addServerPanelLayout.createSequentialGroup()
                        .add(usernameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 122, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(284, 284, 284)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(seperatorLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(addServerPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(addButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .add(portTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        addServerPanelLayout.setVerticalGroup(
            addServerPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(addServerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(addServerPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(addressLabel)
                    .add(httpLabel)
                    .add(addressTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(seperatorLabel)
                    .add(portTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(addServerPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(addServerPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(usernameLabel)
                        .add(usernameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(addButton))
                .addContainerGap())
        );

        serverPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Existing Servers"));

        leadingLabel.setText("Select a server to edit properties:");

        serverTable.setModel(tableModel);
        serverTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        serverTable.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
					JTable temp = (JTable)e.getSource();
					serverTableMouseDoubleClickPerformed(temp);
				}
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        jScrollPane1.setViewportView(serverTable);

        editButton.setText("Edit");
        editButton.setEnabled(false);
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        removeButton.setText("Remove");
        removeButton.setEnabled(false);
        removeButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				serverList.remove(serverTable.getSelectedRow());
				serverTable.repaint();
			}
        	
        });

        reconnectButton.setText("Reconnect");
        reconnectButton.setEnabled(false);
        reconnectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reconnectButtonActionPerformed(evt);
            }
        });
  

        org.jdesktop.layout.GroupLayout serverPanelLayout = new org.jdesktop.layout.GroupLayout(serverPanel);
        serverPanel.setLayout(serverPanelLayout);
        serverPanelLayout.setHorizontalGroup(
            serverPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(serverPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(serverPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                    .add(leadingLabel)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, serverPanelLayout.createSequentialGroup()
                        .add(reconnectButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(removeButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(editButton)))
                .addContainerGap())
        );
        serverPanelLayout.setVerticalGroup(
            serverPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(serverPanelLayout.createSequentialGroup()
                .add(20, 20, 20)
                .add(leadingLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(serverPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(editButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(removeButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(reconnectButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(28, 28, 28))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 721, Short.MAX_VALUE)
            .add(0, 721, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, serverPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, addServerPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 360, Short.MAX_VALUE)
            .add(0, 360, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .add(21, 21, 21)
                .add(addServerPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(serverPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(26, 26, 26))
        );
    }

    private void addressTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    private void usernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    private void portTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
       
    	if(addButton.getText().compareTo("Add")==0){
    		if(addressTextField.getText().compareTo(addressTextFieldText)!=0 &&
            		portTextField.getText().compareTo("") != 0 &&
            		usernameTextField.getText().compareTo("") !=0 ){
            	
            	// Create new instance of SensorServer with userInputs
            	SensorServer server = new SensorServer("http://" + addressTextField.getText(), portTextField.getText(), usernameTextField.getText());
            	server.addObserver(this);
            	server.connect();
            	
            	serverList.add(server);
            	serverTable.updateUI();
         
            }
    	}else{
    		
    		List<SensorServer> copy = serverList;
    		List<SensorServer> newlist = new ArrayList<SensorServer>();
    		
    		for(int i = 0; i < copy.size(); i++){
    			if(i!=serverTable.getSelectedRow()){
    				newlist.add(copy.get(i));
    			}else{
    				
                	SensorServer server = new SensorServer("http://" + addressTextField.getText(), portTextField.getText(), usernameTextField.getText());
                	server.addObserver(this);
                	server.connect();
                	
    				newlist.add(server);
    			}
    		}
    		
    		serverList = newlist;
    		
    		addButton.setText("Add");
    		serverTable.repaint();
    	}
    	
    	editButton.setEnabled(true);
    	removeButton.setEnabled(true);
    	reconnectButton.setEnabled(true);
    	serverTable.setEnabled(true);
    }
    
    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	removeButton.setEnabled(false);
    	reconnectButton.setEnabled(false);
    	editButton.setEnabled(false);
    	serverTable.setEnabled(false);
    	
    	addButton.setText("Change");
    	addressTextField.setText(serverList.get(serverTable.getSelectedRow()).getAddress().substring(7));
    	portTextField.setText(serverList.get(serverTable.getSelectedRow()).getPort());
    	usernameTextField.setText(serverList.get(serverTable.getSelectedRow()).getUsername());
    }
    
    private void reconnectButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	serverList.get(serverTable.getSelectedRow()).reconnect();
    }
	
    private void serverTableMouseDoubleClickPerformed(JTable source) {
    	if(serverTable.getRowCount()>0 && serverTable.isEnabled()){
        	if(serverList.get(source.getSelectedRow()).getConnectionStatus().toString().compareTo("online")==0){
        		ServerDetailFrame frame = new ServerDetailFrame(source.getSelectedRow());
        		frame.setLocationRelativeTo(null);
        		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        		frame.pack();
        		frame.setVisible(true);
        	}
    	}
	}
    
	public void update(Observable o, Object arg) {
		if(o instanceof peripheral.logic.sensor.SensorServer && arg instanceof ServerConnectionStatusChangedEvent){
			serverTable.repaint();
		}
	}
    
    class SensorTableModel extends AbstractTableModel{

		private static final long serialVersionUID = 1L;
		private String[] columnNames = {"Server", "Username", "Status"};
		
		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return serverList.size();
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			
			switch (columnIndex) {
			case 0:	return serverList.get(rowIndex).getAddress();
			case 1:	return serverList.get(rowIndex).getUsername();
			case 2: return serverList.get(rowIndex).getConnectionStatus();
			default: return null;
			}
			
		}
    	
       @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }
    }
    
    class ServerDetailFrame extends JFrame {
    	
		private static final long serialVersionUID = 1L;
		
		private SensorServer server;
    	private Sensor selectedSensor;
    	private ServerDetailFrame mySelf;
    	
        public ServerDetailFrame(int listindex) {
        	this.server = serverList.get(listindex);
        	this.mySelf = this;
            initComponents();
        }

        private void initComponents() {

            jddacLogoLabel = new javax.swing.JLabel();
            addLabel = new javax.swing.JLabel();
            usrLabel = new javax.swing.JLabel();
            addText = new javax.swing.JLabel();
            usrText = new javax.swing.JLabel();
            numLabel = new javax.swing.JLabel();
            numChanLabel = new javax.swing.JLabel();
            numText = new javax.swing.JLabel();
            numChanText = new javax.swing.JLabel();
            metadataPanel = new javax.swing.JPanel();
            nameLabel = new javax.swing.JLabel();
            descriptionLabel = new javax.swing.JLabel();
            datatypeLabel = new javax.swing.JLabel();
            unitsLabel = new javax.swing.JLabel();
            locationLabel = new javax.swing.JLabel();
            upperLabel = new javax.swing.JLabel();
            lowerLabel = new javax.swing.JLabel();
            nameText = new javax.swing.JLabel();
            descriptionText = new javax.swing.JLabel();
            datatypeText = new javax.swing.JLabel();
            unitsText = new javax.swing.JLabel();
            locationText = new javax.swing.JLabel();
            upperText = new javax.swing.JLabel();
            lowerText = new javax.swing.JLabel();
            sensorComboBox = new javax.swing.JComboBox();
            channelComboBox = new javax.swing.JComboBox();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

            jddacLogoLabel.setIcon(new javax.swing.ImageIcon(System.getProperty("user.dir") + "/res/JddacLogo.gif"));

            addLabel.setText("Address:");

            usrLabel.setText("Username:");

            addText.setText(server.getAddress() + "/" + server.getPort());

            usrText.setText(server.getUsername());

            numLabel.setText("Number of Sensors:");

            numChanLabel.setText("Total Number of Measurement Channels:");

            numText.setText(String.valueOf(server.getSensorList().size()));
            
            int channels = 0;
            
            for(Sensor sensor : server.getSensorList()){
            	channels += sensor.getSensorChannels().size();
            }
            
            numChanText.setText(String.valueOf(channels));

            metadataPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Channel Metadata"));
            metadataPanel.setVisible(false);

            nameLabel.setText("Name:");

            descriptionLabel.setText("Description:");

            datatypeLabel.setText("Datatype:");

            unitsLabel.setText("Units:");

            locationLabel.setText("Location:");

            upperLabel.setText("Upperlimit:");

            lowerLabel.setText("Lowerlimit:");

            org.jdesktop.layout.GroupLayout metadataPanelLayout = new org.jdesktop.layout.GroupLayout(metadataPanel);
            metadataPanel.setLayout(metadataPanelLayout);
            metadataPanelLayout.setHorizontalGroup(
                metadataPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(metadataPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .add(metadataPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(descriptionLabel)
                        .add(datatypeLabel)
                        .add(unitsLabel)
                        .add(locationLabel)
                        .add(upperLabel)
                        .add(lowerLabel)
                        .add(nameLabel))
                    .add(18, 18, 18)
                    .add(metadataPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(nameText)
                        .add(lowerText)
                        .add(upperText)
                        .add(locationText)
                        .add(unitsText)
                        .add(datatypeText)
                        .add(descriptionText))
                    .addContainerGap(209, Short.MAX_VALUE))
            );
            metadataPanelLayout.setVerticalGroup(
                metadataPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(metadataPanelLayout.createSequentialGroup()
                    .add(metadataPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(nameText)
                        .add(nameLabel))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(metadataPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(descriptionLabel)
                        .add(descriptionText))
                    .add(18, 18, 18)
                    .add(metadataPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(datatypeLabel)
                        .add(datatypeText))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(metadataPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(unitsLabel)
                        .add(unitsText))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(metadataPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(locationLabel)
                        .add(locationText))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(metadataPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(upperLabel)
                        .add(upperText))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(metadataPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(lowerLabel)
                        .add(lowerText))
                    .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            String [] sensorModel = new String[server.getSensorList().size()];
            
            for(int i = 0; i<sensorModel.length; i++){
            	sensorModel[i] = server.getSensorList().get(i).getName();
            }
            
            sensorComboBox.setModel(new javax.swing.DefaultComboBoxModel(sensorModel));
            sensorComboBox.addPopupMenuListener(new PopupMenuListener(){

				public void popupMenuCanceled(PopupMenuEvent e) {
					
				}

				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
					if(sensorComboBox.getSelectedIndex() != 0){
						
						ArrayList<SensorChannel> chan = server.getSensorList().get(sensorComboBox.getSelectedIndex()).getSensorChannels();
						String [] channelModel = new String[server.getSensorList().get(sensorComboBox.getSelectedIndex()).getSensorChannels().size()];
						
						for(int i = 0; i<channelModel.length; i++){
			            	channelModel[i] = chan.get(i).getMetadata().get("shortname");
			            }
						
						channelComboBox.setModel(new javax.swing.DefaultComboBoxModel(channelModel));
						channelComboBox.setEnabled(true);
						
						selectedSensor = server.getSensorList().get(sensorComboBox.getSelectedIndex());
					}
				}

				public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				}
            	
            });

            channelComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select a channel" }));
            channelComboBox.setEnabled(false);
            
            channelComboBox.addPopupMenuListener(new PopupMenuListener(){

				public void popupMenuCanceled(PopupMenuEvent e) {
					
				}

				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
					SensorChannel selection = selectedSensor.getSensorChannels().get(channelComboBox.getSelectedIndex());
					
		            nameText.setText(selection.getMetadata().get("shortname"));
		            descriptionText.setText(selection.getMetadata().get("description"));
		            datatypeText.setText(selection.getMetadata().get("datatype"));
		            unitsText.setText(selection.getMetadata().get("units"));
		            locationText.setText(selection.getMetadata().get("location"));
		            upperText.setText(selection.getMetadata().get("upperlimit"));
		            lowerText.setText(selection.getMetadata().get("lowerlimit"));
					
		            if(!metadataPanel.isVisible()){
		            	metadataPanel.setVisible(true);
			            mySelf.pack();
		            }
		            
				}

				public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
					
				}
            	
            });

            org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, sensorComboBox, 0, 470, Short.MAX_VALUE)
                                .add(channelComboBox, 0, 470, Short.MAX_VALUE)))
                        .add(layout.createSequentialGroup()
                            .add(172, 172, 172)
                            .add(jddacLogoLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 154, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(layout.createSequentialGroup()
                                    .add(addLabel)
                                    .add(29, 29, 29)
                                    .add(addText))
                                .add(layout.createSequentialGroup()
                                    .add(usrLabel)
                                    .add(18, 18, 18)
                                    .add(usrText))
                                .add(layout.createSequentialGroup()
                                    .add(numLabel)
                                    .add(18, 18, 18)
                                    .add(numText))
                                .add(layout.createSequentialGroup()
                                    .add(numChanLabel)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                    .add(numChanText))))
                        .add(layout.createSequentialGroup()
                            .addContainerGap()
                            .add(metadataPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jddacLogoLabel)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, addLabel)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, addText))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(usrText)
                        .add(usrLabel))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(numLabel)
                        .add(numText))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(numChanLabel)
                        .add(numChanText))
                    .add(24, 24, 24)
                    .add(sensorComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(channelComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(18, 18, 18)
                    .add(metadataPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            pack();
        }

        private javax.swing.JLabel addLabel;
        private javax.swing.JLabel addText;
        private javax.swing.JComboBox channelComboBox;
        private javax.swing.JLabel datatypeLabel;
        private javax.swing.JLabel datatypeText;
        private javax.swing.JLabel descriptionLabel;
        private javax.swing.JLabel descriptionText;
        private javax.swing.JLabel jddacLogoLabel;
        private javax.swing.JLabel locationLabel;
        private javax.swing.JLabel locationText;
        private javax.swing.JLabel lowerLabel;
        private javax.swing.JLabel lowerText;
        private javax.swing.JPanel metadataPanel;
        private javax.swing.JLabel nameLabel;
        private javax.swing.JLabel nameText;
        private javax.swing.JLabel numChanLabel;
        private javax.swing.JLabel numChanText;
        private javax.swing.JLabel numLabel;
        private javax.swing.JLabel numText;
        private javax.swing.JComboBox sensorComboBox;
        private javax.swing.JLabel unitsLabel;
        private javax.swing.JLabel unitsText;
        private javax.swing.JLabel upperLabel;
        private javax.swing.JLabel upperText;
        private javax.swing.JLabel usrLabel;
        private javax.swing.JLabel usrText;
    }
}
