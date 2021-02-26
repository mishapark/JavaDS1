package Presentation;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

//import Presentation.Validator;
import data.TextFileIO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class Record extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Record frame = new Record();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Record() {
		setResizable(false);
		setTitle("Record");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rbCity = new JRadioButton("City");
		buttonGroup.add(rbCity);
		rbCity.setBounds(154, 18, 67, 23);
		contentPane.add(rbCity);
		
		JRadioButton rbAll = new JRadioButton("All");
		buttonGroup.add(rbAll);
		rbAll.setSelected(true);
		rbAll.setBounds(25, 18, 58, 23);
		contentPane.add(rbAll);
		
		JRadioButton rbDate = new JRadioButton("Date");
		buttonGroup.add(rbDate);
		rbDate.setBounds(85, 18, 67, 23);
		contentPane.add(rbDate);
		
		JRadioButton rbCD = new JRadioButton("Date & City");
		buttonGroup.add(rbCD);
		rbCD.setBounds(217, 18, 109, 23);
		contentPane.add(rbCD);
		
		txtSearch = new JTextField();
		txtSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSearch.setColumns(10);
		txtSearch.setBounds(85, 45, 142, 20);
		contentPane.add(txtSearch);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(35, 48, 78, 14);
		contentPane.add(lblSearch);
		
		JTextArea taSearch = new JTextArea();
		taSearch.setEditable(false);
		JScrollPane sp = new JScrollPane(taSearch, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(25, 92, 375, 158);
		contentPane.add(sp);
		
		JLabel lblCases = new JLabel("");
		lblCases.setBounds(25, 266, 222, 14);
		contentPane.add(lblCases);
		
		JLabel lblDeaths = new JLabel("");
		lblDeaths.setBounds(25, 291, 253, 14);
		contentPane.add(lblDeaths);
		
		JLabel lblRecovered = new JLabel("");
		lblRecovered.setBounds(25, 316, 301, 14);
		contentPane.add(lblRecovered);

		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String findStr = txtSearch.getText();
				taSearch.setText("");
				int cases = 0;
				int deaths = 0;
				int recovered = 0;
				
	                
	                if (rbAll.isSelected()) {
	                	//Resetting labels
						lblCases.setText("");
						lblDeaths.setText("");
						lblRecovered.setText("");
	                	try {
	                		//Show all the cases
	                		Object[] lines = TextFileIO.findAll();
	                		txtSearch.setText("");

	                		for (Object line: lines) {
								taSearch.append(line.toString());
								taSearch.append("\n");

								//Calculating total cases, total deaths and total recoveries
								String[] lineData = line.toString().split(",");
								cases += Integer.parseInt(lineData[2].toString());
								lblCases.setText("Total cases: " + cases);
								deaths += Integer.parseInt(lineData[3].toString());
								lblDeaths.setText("Total deaths: " + deaths);
								recovered += Integer.parseInt(lineData[4].toString());
								lblRecovered.setText("Total recovered: " + recovered);
							}
						} catch (IOException ioException) {
	                		//Print the error
							ioException.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error! " + ioException.getMessage());
						}
	                	
	                } else {
	            		if ((Validator.isPresent(txtSearch, "Search"))) {
							if (rbDate.isSelected()) {
								//Resetting labels
								lblCases.setText("");
								lblDeaths.setText("");
								lblRecovered.setText("");
								try {
									//Show only cases by specific date
									Object[] lines = TextFileIO.findDate(txtSearch.getText());
									for (Object line: lines) {
										taSearch.append(line.toString());
										taSearch.append("\n");

										//Calculating total cases, total deaths and total recoveries
										String[] lineData = line.toString().split(",");
										cases += Integer.parseInt(lineData[2]);
				                		lblCases.setText("Total cases: " + cases);
				                		deaths += Integer.parseInt(lineData[3]);
				                		lblDeaths.setText("Total deaths: " + deaths);
				                		recovered += Integer.parseInt(lineData[4]);
				                		lblRecovered.setText("Total recovered: " + recovered);
									}
								} catch (IOException ioException) {
									//Print the error
									ioException.printStackTrace();
									JOptionPane.showMessageDialog(null, "Error! " + ioException.getMessage());
								}

							} else if (rbCity.isSelected()) {
								//Resetting labels
								lblCases.setText("");
								lblDeaths.setText("");
								lblRecovered.setText("");
								try {
									//Show cases by city
									Object[] lines = TextFileIO.findCity(txtSearch.getText());
									for (Object line: lines) {
										taSearch.append(line.toString());
										taSearch.append("\n");

										//Calculating total cases, total deaths and total recoveries
										String[] lineData = line.toString().split(",");
										cases += Integer.parseInt(lineData[2]);
										lblCases.setText("Total cases: " + cases);
										deaths += Integer.parseInt(lineData[3]);
										lblDeaths.setText("Total deaths: " + deaths);
										recovered += Integer.parseInt(lineData[4]);
										lblRecovered.setText("Total recovered: " + recovered);
									}
								} catch (IOException ioException) {
									//Print the error
									ioException.printStackTrace();
									JOptionPane.showMessageDialog(null, "Error! " + ioException.getMessage());
								}

							} else if (rbCD.isSelected()) {
								//Resetting labels
								lblCases.setText("");
								lblDeaths.setText("");
								lblRecovered.setText("");
								try {
									//Searching cases by date and city
									Object[] lines = TextFileIO.findDateAndCity(txtSearch.getText());
									for (Object line: lines) {
										taSearch.append(line.toString());
										taSearch.append("\n");

										String[] lineData = line.toString().split(",");
										cases += Integer.parseInt(lineData[2]);
										lblCases.setText("Total cases: " + cases);
										deaths += Integer.parseInt(lineData[3]);
										lblDeaths.setText("Total deaths: " + deaths);
										recovered += Integer.parseInt(lineData[4]);
										lblRecovered.setText("Total recovered: " + recovered);
									}
								} catch (IOException ioException) {
									//Print the error
									ioException.printStackTrace();
									JOptionPane.showMessageDialog(null, "Error! " + ioException.getMessage());
								}
							}
						}
	                }	
			}
		});
		btnFind.setBounds(237, 44, 89, 23);
		contentPane.add(btnFind);
		
		
		
		
		
	}
}
