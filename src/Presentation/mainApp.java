package Presentation;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.TextFileIO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class mainApp extends JFrame {

	private JPanel contentPane;
	private JTextField txtDate;
	private JTextField txtCity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainApp frame = new mainApp();
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
	public mainApp() {
		setResizable(false);
		setTitle("COVID-19 Record");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 267);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDateWarn = new JLabel("");
		lblDateWarn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDateWarn.setForeground(Color.RED);
		lblDateWarn.setBounds(236, 38, 153, 14);
		contentPane.add(lblDateWarn);
		
		JLabel lblCityWarn = new JLabel("");
		lblCityWarn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCityWarn.setForeground(Color.RED);
		lblCityWarn.setBounds(236, 73, 153, 14);
		contentPane.add(lblCityWarn);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate.setBounds(10, 37, 71, 14);
		contentPane.add(lblDate);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCity.setBounds(10, 73, 71, 14);
		contentPane.add(lblCity);
		
		JLabel lblCases = new JLabel("Cases: ");
		lblCases.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCases.setBounds(10, 109, 71, 14);
		contentPane.add(lblCases);
		
		JLabel lblDeaths = new JLabel("Deaths:");
		lblDeaths.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDeaths.setBounds(10, 146, 71, 14);
		contentPane.add(lblDeaths);
		
		JLabel lblRecovered = new JLabel("Recovered:");
		lblRecovered.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRecovered.setBounds(10, 188, 71, 14);
		contentPane.add(lblRecovered);
		
		txtDate = new JTextField();
		txtDate.setBounds(87, 33, 132, 20);
		contentPane.add(txtDate);
		txtDate.setColumns(10);
		
		txtCity = new JTextField();
		txtCity.setColumns(10);
		txtCity.setBounds(87, 69, 132, 20);
		contentPane.add(txtCity);
		
		JSpinner spCases = new JSpinner();
		spCases.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spCases.setBounds(87, 105, 57, 20);
		contentPane.add(spCases);
		
		JSpinner spDeaths = new JSpinner();
		spDeaths.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spDeaths.setBounds(87, 142, 57, 20);
		contentPane.add(spDeaths);
		
		JSpinner spRecovered = new JSpinner();
		spRecovered.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spRecovered.setBounds(87, 184, 57, 20);
		contentPane.add(spRecovered);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String date = txtDate.getText();
				String city = txtCity.getText();
				int cases = (int)spCases.getValue();
				int deaths = (int)spDeaths.getValue();
				int recovered = (int)spRecovered.getValue();
				
				boolean isLetter = false;
				if (city.matches("(?<=\\s|^)[a-zA-Z]*(?=[.,;:]?\\s|$)")) {
					isLetter = true;
				}
				
				if(Validator.isPresent(txtDate, "Date") && Validator.isPresent(txtCity, "City")) {
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu");
					try {
						lblDateWarn.setText("");
						formatter.parse(date);
					} catch (Exception e1) {
						lblDateWarn.setText("Date format is MM/DD/YYYY");
						return;
					}
					
					if(isLetter) {
						lblCityWarn.setText("");
					} else {
						lblCityWarn.setText("Enter letters please");
						return;
					}
					
					String entry = date + "," + city + "," + "Cases: " + cases + "," + "Deaths: " + deaths + "," + "Recovered: " + recovered;
					
					try {
						TextFileIO.writeData(entry);
						JOptionPane.showMessageDialog(null, "Data is saved");
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Error! " + e1.getMessage());
					}
				}
			}
		});
		btnSave.setBounds(253, 184, 89, 23);
		contentPane.add(btnSave);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 405, 22);
		contentPane.add(menuBar);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenuItem mniRecord = new JMenuItem("Record");
		mniRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Record rec = new Record();
				rec.setVisible(true);
			}
		});
		mnView.add(mniRecord);
	}
}















