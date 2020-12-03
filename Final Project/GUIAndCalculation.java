package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
////////////////FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
//Title: FinalProject
//Files: GUIAndCalculation.java, TableCol.java
//Author: ZHAN YU
//Email: zyu293@wisc.edu
//Course: SU20 CS 400 002
//Deadline: 8/08/2020
//Description: This class aims create a GUI for user to load files and then to calculate the results
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
//Students who get help from sources other than their partner and the course
//staff must fully acknowledge and credit those sources here. If you did not
//receive any help of any kind from outside sources, explicitly indicate NONE
//next to each of the labels below.
//
//Persons: NONE
//Online Sources: 
//
///////////////////////////////////////////////////////////////////////////////
/***
 * This class aims create a GUI for user to load files and then to calculate the results
 * @author Zhan Yu
 *
 */
public class GUIAndCalculation extends Application {
	// variables for requiring information
	private Label label;// Label for telling user to enter something
	private Label warn;// Label for telling user to enter each Get button only once
	private Label year;// Label for indicating the place for entering year
	private Label farmID;// Label for indicating the place for entering farm ID
	private Label month;// Label for indicating the place for entering month
	private Label dateRange1;// Label for indicating the place for entering start date
	private Label dateRange2;// Label for indicating the place for entering end date
	private Label file;// Label for indicating the place for entering file location
	private TextField inputFilePath; // TextField for user to enter file location
	private TextField inputYear; // TextField for user to enter year
	private TextField inputID; // TextField for user to enter ID
	private TextField inputMonth; // TextField for user to enter month
	private TextField startDate; // TextField for user to enter start date of date range
	private TextField endDate; // TextField for user to enter end date of date range

	// variables for farm report part
	private Label farmReportLabel;
	private TableView table1;
	private TableView table2;
	private Button calcButton1; // Triggers milk weight calculation
	private Button calcButton2; // Triggers milk weight calculation

	// variables for annual report part
	private Label annualReportLabel;
	private TableView table3;
	private TableView table4;
	private Button calcButton3; // Triggers milk weight calculation
	private Button calcButton4; // Triggers milk weight calculation

	// variables for monthly report part
	private Label monthlyReportLabel;
	private TableView table6;
	private TableView table7;
	private Button calcButton6; // Triggers milk weight calculation
	private Button calcButton7; // Triggers milk weight calculation

	// variables for date range report part
	private Label dateRangeReportLabel;
	private TableView table8;
	private TableView table9;
	private Button calcButton8; // Triggers milk weight calculation
	private Button calcButton9; // Triggers milk weight calculation

	// variables for displaying min/max/average data
	private TableView table5;
	private TableView table10;
	private Button calcButton5;// Triggers milk weight calculation
	private Button calcButton10;// Triggers milk weight calculation

	// variables for exiting the program
	private Button calcButton11;// Triggers exit
	public Scanner sc1;
	public Scanner sc2;
	private ObservableList<TableCol> data = FXCollections.observableArrayList();
	
	private String folderPath = ""; //user entered path to files' folder
	/**
	 * This method aims to start the function of the application
	 */
	@Override
	public void start(Stage applicationStage) {
		Scene scene = null; // Scene contains all content
		GridPane gridPane = null; // Positions components within scene
		gridPane = new GridPane(); // Create an empty pane
		gridPane.setPadding(new Insets(2, 2, 2, 2)); // Padding around grid
		gridPane.setHgap(1); // Spacing between columns
		gridPane.setVgap(1); // Spacing between rows
		gridPane.setMaxSize(1500, 800);

		// part for entering information
		label = new Label("Enter data in the box:");
		warn = new Label("Press each Get button ONCE(Except reopen)!");
		file = new Label("FolderPath:");
		inputFilePath = new TextField();
		inputFilePath.setPrefColumnCount(8);
		inputFilePath.setEditable(true);
		inputFilePath.setText("e.g.C:\\eclipse 2020.6\\FinalProject\\small\\");
		year = new Label("Year:");
		month = new Label("Month:");
		farmID = new Label("Farm ID:");
		dateRange1 = new Label("Enter date range: From");
		dateRange2 = new Label("to");
		inputYear = new TextField();
		inputYear.setPrefColumnCount(8);
		inputYear.setEditable(true);
		inputYear.setText("e.g. 2019");
		inputID = new TextField();
		inputID.setPrefColumnCount(8);
		inputID.setEditable(true);
		inputID.setText("e.g. 2");
		inputMonth = new TextField();
		inputMonth.setPrefColumnCount(8);
		inputMonth.setEditable(true);
		inputMonth.setText("e.g. 4");
		startDate = new TextField();
		startDate.setPrefColumnCount(8);
		startDate.setEditable(true);
		startDate.setText("e.g. 2019-8-4");
		endDate = new TextField();
		endDate.setPrefColumnCount(8);
		endDate.setEditable(true);
		endDate.setText("e.g. 2019-9-30");
		//button for existing the application
		calcButton11 = new Button("Exit the application");
		calcButton11.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				applicationStage.close();
			}

		});

		// part for farm report
		farmReportLabel = new Label("Farm Report (Based on farm ID and year)");
		table1 = new TableView();
		table1.setEditable(true);
		table2 = new TableView();
		table2.setEditable(true);
		calcButton1 = new Button("Get total weight per month of this farm:");
		calcButton2 = new Button("Get pencentage per month of this farm:");
		// Set an event handler to handle button presses
		calcButton1.setOnAction(new EventHandler<ActionEvent>() {
			/*
			 * Method is automatically called when an event occurs (e.g, button is pressed)
			 * This method aims to get the total weight per month of this farm
			 */
			@Override
			public void handle(ActionEvent event) {
				folderPath = inputFilePath.getText();
				String year = inputYear.getText();
				String ID = inputID.getText();
				boolean goodInput1 = true;
				boolean goodInput2 = true;
				int size1 = year.length();
				int size2 = ID.length();
				int numOfFarms = 0;
				String content = "";
				int monthWeight = 0;
				for (int i = 0; i < size1; i++) {
					if (!(Character.isDigit(year.charAt(i))) || Integer.valueOf(year) > 2020
							|| Integer.valueOf(year) < 0||year.length()>4||year.length()<4) {
						goodInput1 = false;
					}
				}
				for (int i = 0; i < size2; i++) {
					if (!(Character.isDigit(ID.charAt(i))) || Integer.valueOf(ID) < 0||ID.length()>4||ID.length()<1) {
						goodInput2 = false;
					}
				}
				if (goodInput1 && goodInput2) {//the inputs have the right format
					boolean flag1 = true;
					boolean flag2 = true;
					boolean flag3 = true;
					Integer January = 0, February = 0, March = 0, April = 0, May = 0, June = 0, July = 0, August = 0,
							September = 0, October = 0, November = 0, December = 0;
					Integer[] IntegerM = new Integer[] { January, February, March, April, May, June, July, August,
							September, October, November, December };
					String[] contents = new String[100000];
					// check whether the file contains missing or error data
					for (int i = 0; i < 12; i++) {
						String path = folderPath+"\\"+ year + "-" + (i + 1) + ".csv";
						String contentForCheck = "";
						String[] contentsForCheck = new String[100000];
						try {
							sc1 = new Scanner(new File(path));

						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}

						while (sc1.hasNextLine()) {
							contentForCheck += sc1.nextLine() + ",";
						}
						contentsForCheck = contentForCheck.split(",");
						// check the data in the first column of file
						for (int z = 3; z < contentsForCheck.length; z += 3) {
							if (!contentsForCheck[z].contains(year)) {
								flag1 = false;
								break;
							}
						}
						// check the data in the second column of file
						for (int x = 4; x < contentsForCheck.length; x += 3) {

							if (!contentsForCheck[x].contains("Farm")) {
								flag2 = false;
								break;
							}
						}
						// check the data in the third column of file
						for (int y = 5; y < contentsForCheck.length; y += 3) {
							int size = contentsForCheck[y].length();
							for (int p = 0; p < size; p++) {
								if (!(Character.isDigit(contentsForCheck[y].charAt(p)))) {

									flag3 = false;
									break;
								}

							}
						}
					}
					// get one file for get the number of farms
					String path = folderPath+"\\"+ year + "-" + 1 + ".csv";

					try {
						sc2 = new Scanner(new File(path));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}

					while (sc2.hasNextLine()) {
						content += sc2.nextLine() + ",";
					}
					contents = content.split(",");

					// get the farms ID of the contents
					String farmID = "";
					for (int u = 1; u < contents.length; u += 3) {
						if (!contents[u].equals("farm_id")) {
							farmID += contents[u].substring(5, contents[u].length()) + ",";
						}
					}
					// put the farms'ID into array
					String[] farmsIDUnsorted = new String[10000];
					farmsIDUnsorted = farmID.split(",");
					// sort the ID array
					int[] farmsID = new int[farmsIDUnsorted.length];
					for (int index0 = 0; index0 < farmsID.length; index0++) {
						farmsID[index0] = Integer.valueOf(farmsIDUnsorted[index0]);
					}
					Arrays.sort(farmsID);
					// remove duplicate item
					ArrayList<Integer> farmIDs = new ArrayList<Integer>();
					for (int q = 0; q < farmsID.length; q++) {
						if (!farmIDs.contains(farmsID[q]))
							farmIDs.add(farmsID[q]);
					}
					numOfFarms = farmIDs.size();
					// check whether the information of the input farm ID is contained in the file
					boolean flag4 = false;
					for (int y = 0; y < farmIDs.size(); y++) {
						if (String.valueOf(farmIDs.get(y)).equals(ID)) {
							flag4 = true;
							break;
						}
					}
					if (flag4) {//the farm's data is contained in the file
						if (flag1 && flag2 && flag3) {// the files have proper data
							content = "";
							contents = new String[contents.length];
							int[] monthWeights = new int[contents.length];
							for (int i = 0; i < 12; i++) {
								String path1 = folderPath + "\\"+ year + "-" + (i + 1) + ".csv";
								// get the contents
								try {
									sc2 = new Scanner(new File(path1));

								} catch (FileNotFoundException e) {
									e.printStackTrace();
								}

								while (sc2.hasNextLine()) {
									content += sc2.nextLine() + ",";
								}
								contents = content.split(",");
								// get the total weight produced by the farm each month
								for (int p = 0; p < contents.length; p++) {
									if (contents[p].equals("Farm " + ID)) {
										monthWeights[i] += Integer.valueOf(contents[p + 1]);
									}
								}

								content = "";
								contents = new String[contents.length];
							}
							data = FXCollections.observableArrayList(new TableCol("1", String.valueOf(monthWeights[0])),
									new TableCol("2", String.valueOf(monthWeights[1])),
									new TableCol("3", String.valueOf(monthWeights[2])),
									new TableCol("4", String.valueOf(monthWeights[3])),
									new TableCol("5", String.valueOf(monthWeights[4])),
									new TableCol("6", String.valueOf(monthWeights[5])),
									new TableCol("7", String.valueOf(monthWeights[6])),
									new TableCol("8", String.valueOf(monthWeights[7])),
									new TableCol("9", String.valueOf(monthWeights[8])),
									new TableCol("10", String.valueOf(monthWeights[9])),
									new TableCol("11", String.valueOf(monthWeights[10])),
									new TableCol("12", String.valueOf(monthWeights[11])));
						} else {
							data = FXCollections.observableArrayList(
									new TableCol("Error!", "Please make sure that data in your files are proper."));

						}
					} else {
						data = FXCollections.observableArrayList(
								new TableCol("Error!", "Please make sure that the farm's ID exists in the file."));
					}

					TableColumn firstCol = new TableColumn("#Month");
					TableColumn secondCol = new TableColumn("totalWeight");
					firstCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("ID"));
					secondCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("totalWeight"));
					table1.setItems(data);
					table1.getColumns().addAll(firstCol, secondCol);
				} else {
					data = FXCollections
							.observableArrayList(new TableCol("Error!", "Make sure your inputs are digit."));
					TableColumn firstCol = new TableColumn("#Month");
					TableColumn secondCol = new TableColumn("totalWeight");
					firstCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("ID"));
					secondCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("totalWeight"));
					table1.setItems(data);
					table1.getColumns().addAll(firstCol, secondCol);
				}

			}
		});

		calcButton2.setOnAction(new EventHandler<ActionEvent>() {
			/*
			 * Method is automatically called when an event occurs (e.g, button is pressed)\
			 * This method aims to get percentage per month of this farm
			 */
			@Override
			public void handle(ActionEvent event) {
				folderPath = inputFilePath.getText();
				String year = inputYear.getText();
				String ID = inputID.getText();
				boolean goodInput1 = true;
				boolean goodInput2 = true;
				int size1 = year.length();
				int size2 = ID.length();
				int numOfFarms = 0;
				String content = "";
				int monthWeight = 0;
				for (int i = 0; i < size1; i++) {
					if (!(Character.isDigit(year.charAt(i))) || Integer.valueOf(year) > 2020
							|| Integer.valueOf(year) < 0||year.length()>4||year.length()<4) {
						goodInput1 = false;
					}
				}
				for (int i = 0; i < size2; i++) {
					if (!(Character.isDigit(ID.charAt(i))) || Integer.valueOf(ID) < 0||ID.length()<1||ID.length()>4) {
						goodInput2 = false;
					}
				}
				if (goodInput1 && goodInput2) {// the inputs have the right format
					boolean flag1 = true;
					boolean flag2 = true;
					boolean flag3 = true;
					String[] contents = new String[100000];
					// check whether the file contains missing or error data
					for (int i = 0; i < 12; i++) {
						String path = folderPath+"\\"+ year + "-" + (i + 1) + ".csv";
						String contentForCheck = "";
						String[] contentsForCheck = new String[100000];
						try {
							sc1 = new Scanner(new File(path));

						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}

						while (sc1.hasNextLine()) {
							contentForCheck += sc1.nextLine() + ",";
						}
						contentsForCheck = contentForCheck.split(",");
						// check the data in the first column of file
						for (int z = 3; z < contentsForCheck.length; z += 3) {
							if (!contentsForCheck[z].contains(year)) {
								flag1 = false;
								break;
							}
						}
						// check the data in the second column of file
						for (int x = 4; x < contentsForCheck.length; x += 3) {

							if (!contentsForCheck[x].contains("Farm")) {
								flag2 = false;
								break;
							}
						}
						// check the data in the third column of file
						for (int y = 5; y < contentsForCheck.length; y += 3) {
							int size = contentsForCheck[y].length();
							for (int p = 0; p < size; p++) {
								if (!(Character.isDigit(contentsForCheck[y].charAt(p)))) {

									flag3 = false;
									break;
								}

							}
						}
					}
					// get one file for get the number of farms
					String path = folderPath+"\\"+ year + "-" + 1 + ".csv";

					try {
						sc2 = new Scanner(new File(path));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}

					while (sc2.hasNextLine()) {
						content += sc2.nextLine() + ",";
					}
					contents = content.split(",");

					// get the farms ID of the contents
					String farmID = "";
					for (int u = 1; u < contents.length; u += 3) {
						if (!contents[u].equals("farm_id")) {
							farmID += contents[u].substring(5, contents[u].length()) + ",";
						}
					}
					// put the farms'ID into array
					String[] farmsIDUnsorted = new String[10000];
					farmsIDUnsorted = farmID.split(",");
					// sort the ID array
					int[] farmsID = new int[farmsIDUnsorted.length];
					for (int index0 = 0; index0 < farmsID.length; index0++) {
						farmsID[index0] = Integer.valueOf(farmsIDUnsorted[index0]);
					}
					Arrays.sort(farmsID);
					// remove duplicate item
					ArrayList<Integer> farmIDs = new ArrayList<Integer>();
					for (int q = 0; q < farmsID.length; q++) {
						if (!farmIDs.contains(farmsID[q]))
							farmIDs.add(farmsID[q]);
					}
					numOfFarms = farmIDs.size();
					// check whether the information of the input farm ID is contained in the file
					boolean flag4 = false;
					for (int y = 0; y < farmIDs.size(); y++) {
						if (String.valueOf(farmIDs.get(y)).equals(ID)) {
							flag4 = true;
							break;
						}
					}
					if (flag4) {//the farm's data is contained in the file
						if (flag1 && flag2 && flag3) {// the files have proper data
							content = "";
							contents = new String[contents.length];
							double[] monthWeights = new double[contents.length];
							int[] monthTotalWeights = new int[contents.length];

							for (int i = 0; i < 12; i++) {
								String path1 = folderPath+"\\"+ year + "-" + (i + 1) + ".csv";
								// get the contents
								try {
									sc2 = new Scanner(new File(path1));

								} catch (FileNotFoundException e) {
									e.printStackTrace();
								}
								while (sc2.hasNextLine()) {
									content += sc2.nextLine() + ",";
								}
								contents = content.split(",");
								// get the total weight produced by the farm each month
								for (int p = 4; p < contents.length; p += 3) {
									if (contents[p].equals("Farm " + ID)) {
										monthWeights[i] += Integer.valueOf(contents[p + 1]);
									}
								}

								// get the total weight produced by all farms each month
								for (int s = 5; s < contents.length; s += 3) {
									if (Character.isDigit(contents[s].charAt(0))) {
										monthTotalWeights[i] += Integer.parseInt(contents[s]);
									}
								}
								content = "";
								contents = new String[contents.length];
							}
							// get the percentage of this farm each month
							double[] percentage = new double[12];
							for (int p = 0; p < 12; p++) {
								percentage[p] = (monthWeights[p] / monthTotalWeights[p]) * 100;
							}

							data = FXCollections.observableArrayList(
									new TableCol("1", String.valueOf((percentage[0]) + " %")),
									new TableCol("2", String.valueOf((percentage[1]) + " %")),
									new TableCol("3", String.valueOf((percentage[2]) + " %")),
									new TableCol("4", String.valueOf((percentage[3]) + " %")),
									new TableCol("5", String.valueOf((percentage[4]) + " %")),
									new TableCol("6", String.valueOf((percentage[5]) + " %")),
									new TableCol("7", String.valueOf((percentage[6]) + " %")),
									new TableCol("8", String.valueOf((percentage[7]) + " %")),
									new TableCol("9", String.valueOf((percentage[8]) + " %")),
									new TableCol("10", String.valueOf((percentage[9]) + " %")),
									new TableCol("11", String.valueOf((percentage[10]) + " %")),
									new TableCol("12", String.valueOf((percentage[11]) + " %")));
						} else {
							data = FXCollections.observableArrayList(
									new TableCol("Error!", "Please make sure that data in your files are proper."));

						}
					} else {
						data = FXCollections.observableArrayList(
								new TableCol("Error!", "Please make sure that the farm's ID exists in the file."));
					}

					TableColumn firstCol = new TableColumn("#Month");
					TableColumn secondCol = new TableColumn("Percentage:   %");
					firstCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("ID"));
					secondCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("totalWeight"));
					table2.setItems(data);
					table2.getColumns().addAll(firstCol, secondCol);
				} else {
					data = FXCollections
							.observableArrayList(new TableCol("Error!", "Make sure your inputs are digit."));
					TableColumn firstCol = new TableColumn("#Month");
					TableColumn secondCol = new TableColumn("totalWeight");
					firstCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("ID"));
					secondCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("totalWeight"));
					table2.setItems(data);
					table2.getColumns().addAll(firstCol, secondCol);
				}
			}
		});

		// part for annual report
		annualReportLabel = new Label("Annual Report (Based on year)");
		table3 = new TableView();
		table3.setEditable(true);
		table4 = new TableView();
		table4.setEditable(true);
		calcButton3 = new Button("Get total weight per farm this year:");
		calcButton4 = new Button("Get percentage of each farm this year:");

		// Set an event handler to handle button presses
		calcButton3.setOnAction(new EventHandler<ActionEvent>() {
			/*
			 * Method is automatically called when an event occurs (e.g, button is pressed)
			 * This method aims to get total milk weight of all farms this year
			 */
			@Override
			public void handle(ActionEvent event) {
				folderPath = inputFilePath.getText();
				String year = inputYear.getText();
				boolean goodInput = true;
				int inputSize = year.length();
				for (int i = 0; i < inputSize; i++) {
					if (!(Character.isDigit(year.charAt(i))) || Integer.valueOf(year) > 2020
							|| Integer.valueOf(year) < 0||year.length()>4||year.length()<4) {
						goodInput = false;
					}
				}
				if (goodInput) {// the input has the right format
					boolean flag1 = true;
					boolean flag2 = true;
					boolean flag3 = true;
					int numOfFarms = 0;
					// check whether the file contains missing or error data
					for (int i = 0; i < 12; i++) {
						String path = folderPath+"\\"+ year + "-" + (i + 1) + ".csv";
						String contentForCheck = "";
						String[] contentsForCheck = new String[100];
						try {
							sc1 = new Scanner(new File(path));

						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}

						while (sc1.hasNextLine()) {
							contentForCheck += sc1.nextLine() + ",";
						}
						contentsForCheck = contentForCheck.split(",");
						// check the data in the first column of file
						for (int z = 3; z < contentsForCheck.length; z += 3) {
							if (!contentsForCheck[z].contains(year)) {
								flag1 = false;
								break;
							}
						}
						// check the data in the second column of file
						for (int x = 4; x < contentsForCheck.length; x += 3) {
							if (!contentsForCheck[x].contains("Farm")) {
								flag2 = false;
								break;
							}
						}
						// check the data in the third column of file
						for (int y = 5; y < contentsForCheck.length; y += 3) {
							int size = contentsForCheck[y].length();
							for (int p = 0; p < size; p++) {
								if (!(Character.isDigit(contentsForCheck[y].charAt(p)))) {

									flag3 = false;
									break;
								}
							}
						}
					}

					if (flag1 && flag2 && flag3) {// the files have proper data

						// get the total number of farm participated in producing milk
						String farmID = "";
						ArrayList<Integer> farmIDs = null;
						String content = "";
						String[] contents = new String[100000];
						// get the contents of information this year
						for (int i = 0; i < 12; i++) {
							String path = folderPath+"\\"+ year + "-" + (i + 1) + ".csv";
							try {
								sc2 = new Scanner(new File(path));

							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}

							while (sc2.hasNextLine()) {
								content += sc2.nextLine() + ",";
							}
						}
						contents = content.split(",");

						// get the farms ID of the contents
						for (int u = 1; u < contents.length; u += 3) {
							if (!contents[u].equals("farm_id")) {
								farmID += contents[u].substring(5, contents[u].length()) + ",";
							}
						}
						// put the farms'ID into array
						String[] farmsIDUnsorted = new String[10000];
						farmsIDUnsorted = farmID.split(",");
						// sort the ID array
						int[] farmsID = new int[farmsIDUnsorted.length];
						for (int index0 = 0; index0 < farmsID.length; index0++) {
							farmsID[index0] = Integer.valueOf(farmsIDUnsorted[index0]);
						}
						Arrays.sort(farmsID);
						// remove duplicate item
						farmIDs = new ArrayList<Integer>();
						for (int q = 0; q < farmsID.length; q++) {
							if (!farmIDs.contains(farmsID[q]))
								farmIDs.add(farmsID[q]);
						}
						numOfFarms = farmIDs.size();
						// get the total weight for each farm this year
						double[] totalWeight = new double[numOfFarms];
						for (int i = 0; i < numOfFarms; i++) {
							for (int j = 0; j < contents.length; j++) {
								if (contents[j].equals("Farm " + farmIDs.get(i))) {
									totalWeight[i] += (double) Integer.valueOf(contents[j + 1]);
								}
							}
						}

						data = FXCollections.observableArrayList();
						for (int n = 0; n < numOfFarms; n++) {
							data.addAll(new TableCol("Farm " + String.valueOf(farmIDs.get(n)),
									String.valueOf(totalWeight[n])));
						}
					} else {
						data = FXCollections.observableArrayList(
								new TableCol("Error!", "Please make sure that data in your files are proper."));
					}

					TableColumn firstCol = new TableColumn("Farm ID");
					TableColumn secondCol = new TableColumn("Total weight of this year");
					firstCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("ID"));
					secondCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("totalWeight"));
					table3.setItems(data);
					table3.getColumns().addAll(firstCol, secondCol);
				} else {
					data = FXCollections
							.observableArrayList(new TableCol("Error!", "Make sure your inputs are digit."));
					TableColumn firstCol = new TableColumn("#Month");
					TableColumn secondCol = new TableColumn("totalWeight");
					firstCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("ID"));
					secondCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("totalWeight"));
					table3.setItems(data);
					table3.getColumns().addAll(firstCol, secondCol);
				}
			}
		});
		calcButton4.setOnAction(new EventHandler<ActionEvent>() {
			/*
			 * Method is automatically called when an event occurs (e.g, button is pressed)
			 * This method aims to get the percentage of the total milk weight for each farm
			 */
			@Override
			public void handle(ActionEvent event) {
				folderPath = inputFilePath.getText();
				String year = inputYear.getText();
				boolean goodInput = true;
				int inputSize = year.length();
				for (int i = 0; i < inputSize; i++) {
					if (!(Character.isDigit(year.charAt(i))) || Integer.valueOf(year) > 2020
							|| Integer.valueOf(year) < 0||year.length()>4||year.length()<3) {
						goodInput = false;
					}
				}
				if (goodInput) {// the input has the right format
					boolean flag1 = true;
					boolean flag2 = true;
					boolean flag3 = true;
					int numOfFarms = 0;
					// check whether the file contains missing or error data
					for (int i = 0; i < 12; i++) {
						String path = folderPath+"\\"+ year + "-" + (i + 1) + ".csv";
						String contentForCheck = "";
						String[] contentsForCheck = new String[100];
						try {
							sc1 = new Scanner(new File(path));

						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}

						while (sc1.hasNextLine()) {
							contentForCheck += sc1.nextLine() + ",";
						}
						contentsForCheck = contentForCheck.split(",");
						// check the data in the first column of file
						for (int z = 3; z < contentsForCheck.length; z += 3) {
							if (!contentsForCheck[z].contains(year)) {
								flag1 = false;
								break;
							}
						}
						// check the data in the second column of file
						for (int x = 4; x < contentsForCheck.length; x += 3) {
							if (!contentsForCheck[x].contains("Farm")) {
								flag2 = false;
								break;
							}
						}
						// check the data in the third column of file
						for (int y = 5; y < contentsForCheck.length; y += 3) {
							int size = contentsForCheck[y].length();
							for (int p = 0; p < size; p++) {
								if (!(Character.isDigit(contentsForCheck[y].charAt(p)))) {

									flag3 = false;
									break;
								}
							}
						}
					}

					if (flag1 && flag2 && flag3) {// the files have proper data

						// get the total number of farm participated in producing milk
						String farmID = "";
						ArrayList<Integer> farmIDs = null;
						String content = "";
						String[] contents = new String[100000];
						// get the contents of information this year
						for (int i = 0; i < 12; i++) {
							String path = folderPath+"\\"+ year + "-" + (i + 1) + ".csv";
							try {
								sc2 = new Scanner(new File(path));

							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}

							while (sc2.hasNextLine()) {
								content += sc2.nextLine() + ",";
							}
						}
						contents = content.split(",");

						// get the farms ID of the contents
						for (int u = 1; u < contents.length; u += 3) {
							if (!contents[u].equals("farm_id")) {
								farmID += contents[u].substring(5, contents[u].length()) + ",";
							}
						}
						// put the farms'ID into array
						String[] farmsIDUnsorted = new String[10000];
						farmsIDUnsorted = farmID.split(",");
						// sort the ID array
						int[] farmsID = new int[farmsIDUnsorted.length];
						for (int index0 = 0; index0 < farmsID.length; index0++) {
							farmsID[index0] = Integer.valueOf(farmsIDUnsorted[index0]);
						}
						Arrays.sort(farmsID);
						// remove duplicate item
						farmIDs = new ArrayList<Integer>();
						for (int q = 0; q < farmsID.length; q++) {
							if (!farmIDs.contains(farmsID[q]))
								farmIDs.add(farmsID[q]);
						}
						numOfFarms = farmIDs.size();

						// get the total weight for each farm this year
						double[] eachTotal = new double[numOfFarms];
						for (int i = 0; i < numOfFarms; i++) {
							for (int j = 0; j < contents.length; j++) {
								if (contents[j].equals("Farm " + farmIDs.get(i))) {
									eachTotal[i] += (double) Integer.valueOf(contents[j + 1]);
								}
							}
						}
						// get the total weight for all farms this year
						int totalWeight = 0;
						for (int k = 5; k < contents.length; k += 3) {
							if (Character.isDigit(contents[k].charAt(0))) {
								totalWeight += Integer.valueOf(contents[k]);
							}
						}
						double[] percentage = new double[numOfFarms];
						// get the percentage of total weight for each farm
						for (int p = 0; p < numOfFarms; p++) {
							percentage[p] = (eachTotal[p] / totalWeight) * 100;
						}

						data = FXCollections.observableArrayList();
						for (int n = 0; n < numOfFarms; n++) {
							data.addAll(new TableCol("Farm " + String.valueOf(farmIDs.get(n)),
									String.valueOf(String.format("%.2f",percentage[n]) + "%")));
						}
					} else {
						data = FXCollections.observableArrayList(
								new TableCol("Error!", "Please make sure that data in your files are proper."));
					}

					TableColumn firstCol = new TableColumn("Farm ID");
					TableColumn secondCol = new TableColumn("Percentage of total weight %");
					firstCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("ID"));
					secondCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("totalWeight"));
					table4.setItems(data);
					table4.getColumns().addAll(firstCol, secondCol);
				} else {
					data = FXCollections
							.observableArrayList(new TableCol("Error!", "Make sure your inputs are digit."));
					TableColumn firstCol = new TableColumn("Farm ID");
					TableColumn secondCol = new TableColumn("Percentage of total weight %");
					firstCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("ID"));
					secondCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("totalWeight"));
					table4.setItems(data);
					table4.getColumns().addAll(firstCol, secondCol);
				}
			}
		});

		// part for monthly report
		monthlyReportLabel = new Label("Monthly Report (Based on year and month)");
		table6 = new TableView();
		table6.setEditable(true);
		table7 = new TableView();
		table7.setEditable(true);
		calcButton6 = new Button("Get total weight of each farm this month:");
		calcButton7 = new Button("Get percentage this month per farm:");

		calcButton6.setOnAction(new EventHandler<ActionEvent>() {
			/*
			 * Method is automatically called when an event occurs (e.g, button is pressed)
			 * This method aims to get total milk weight produced in a specific month each
			 * farm
			 */
			@Override
			public void handle(ActionEvent event) {
				folderPath = inputFilePath.getText();
				String year = inputYear.getText();
				boolean goodInput1 = true;
				String month = inputMonth.getText();
				boolean goodInput2 = true;
				int inputSize1 = year.length();
				String content = "";
				// check the whether the inputs are valid digit
				for (int i = 0; i < inputSize1; i++) {
					if (!(Character.isDigit(year.charAt(i))) || Integer.valueOf(year) > 2020
							|| Integer.valueOf(year) < 20||year.length()>4||year.length()<4) {
						goodInput1 = false;
					}
				}
				int inputSize2 = month.length();
				for (int i = 0; i < inputSize2; i++) {
					if (!(Character.isDigit(month.charAt(i))) || Integer.valueOf(month) < 0
							|| Integer.valueOf(month) > 12||month.length()>2||month.length()<1) {
						goodInput1 = false;
					}
				}
				if (goodInput1 && goodInput2) {// the inputs have the right format
					float totalWeight = 0;
					String[] contents = new String[100];
					boolean flag1 = true;
					boolean flag2 = true;
					boolean flag3 = true;
					int numOfFarms = 0;
					// check whether the file contains missing or error data
					String path = folderPath+"\\"+ year + "-" + month + ".csv";
					String contentForCheck = "";
					String[] contentsForCheck = new String[100];
					try {
						sc1 = new Scanner(new File(path));

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}

					while (sc1.hasNextLine()) {
						contentForCheck += sc1.nextLine() + ",";
					}
					contentsForCheck = contentForCheck.split(",");
					// check the data in the first column of file
					for (int z = 3; z < contentsForCheck.length; z += 3) {
						if (!contentsForCheck[z].contains(year)) {
							flag1 = false;
							break;
						}
					}
					// check the data in the second column of file
					for (int x = 4; x < contents.length; x += 3) {
						if (!contentsForCheck[x].contains("Farm")) {
							flag2 = false;
							break;
						}
					}
					// check the data in the third column of file
					for (int y = 5; y < contentsForCheck.length; y += 3) {
						int size = contentsForCheck[y].length();
						for (int p = 0; p < size; p++) {
							if (!(Character.isDigit(contentsForCheck[y].charAt(p)))) {
								flag3 = false;
								break;
							}
						}
					}

					if (flag1 && flag2 && flag3) {// the files have proper data
						// get the total number of farm participated in producing milk
						try {
							sc2 = new Scanner(new File(path));

						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}

						while (sc2.hasNextLine()) {
							content += sc2.nextLine() + ",";
						}
						contents = content.split(",");
						// get the farms ID of the contents
						String farmID = "";
						for (int u = 1; u < contents.length; u += 3) {
							if (!contents[u].equals("farm_id")) {
								farmID += contents[u].substring(5, contents[u].length()) + ",";
							}
						}
						// put the farms'ID into array
						String[] farmsIDUnsorted = new String[10000];
						farmsIDUnsorted = farmID.split(",");
						// sort the ID array
						int[] farmsID = new int[farmsIDUnsorted.length];
						for (int index0 = 0; index0 < farmsID.length; index0++) {
							farmsID[index0] = Integer.valueOf(farmsIDUnsorted[index0]);
						}
						Arrays.sort(farmsID);
						// remove duplicate item
						ArrayList<Integer> farmIDs = new ArrayList<Integer>();
						for (int q = 0; q < farmsID.length; q++) {
							if (!farmIDs.contains(farmsID[q]))
								farmIDs.add(farmsID[q]);
						}
						numOfFarms = farmIDs.size();
						// get total weight for each farm this month
						int[] eachTotal = new int[numOfFarms];
						int[] appearTime = new int[numOfFarms];
						for (int r = 0; r < numOfFarms; r++) {
							for (int e = 0; e < contents.length; e++) {
								if (contents[e].equals("Farm " + farmIDs.get(r))) {
									eachTotal[r] += Integer.valueOf(contents[e + 1]);
									appearTime[r] += 1;
								}
							}
						}

						data = FXCollections.observableArrayList();
						for (int n = 0; n < numOfFarms; n++) {
							data.addAll(new TableCol("Farm " + String.valueOf(farmIDs.get(n)),
									String.valueOf(eachTotal[n])));
						}
					} else {
						data = FXCollections.observableArrayList(
								new TableCol("Error!", "Please make sure that data in your files are proper."));
					}

					TableColumn firstCol = new TableColumn("Farm ID");
					TableColumn secondCol = new TableColumn("TotalWeight this month");
					firstCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("ID"));
					secondCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("totalWeight"));
					table6.setItems(data);
					table6.getColumns().addAll(firstCol, secondCol);
				} else {
					data = FXCollections
							.observableArrayList(new TableCol("Error!", "Make sure your inputs are digit."));
					TableColumn firstCol = new TableColumn("Farm ID");
					TableColumn secondCol = new TableColumn("TotalWeight this month");
					firstCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("ID"));
					secondCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("totalWeight"));
					table6.setItems(data);
					table6.getColumns().addAll(firstCol, secondCol);
				}
			}
		});
		calcButton7.setOnAction(new EventHandler<ActionEvent>() {
			/*
			 * Method is automatically called when an event occurs (e.g, button is pressed)
			 * This method aims to get percentage of total milk weight produced in a
			 * specific month each farm
			 */
			@Override
			public void handle(ActionEvent event) {
				folderPath = inputFilePath.getText();
				String year = inputYear.getText();
				boolean goodInput1 = true;
				String month = inputMonth.getText();
				boolean goodInput2 = true;
				int inputSize1 = year.length();
				String content = "";
				// check the whether the inputs are valid digit
				for (int i = 0; i < inputSize1; i++) {
					if (!(Character.isDigit(year.charAt(i))) || Integer.valueOf(year) > 2020
							|| Integer.valueOf(year) < 20||year.length()>4||year.length()<4) {
						goodInput1 = false;
					}
				}
				int inputSize2 = month.length();
				for (int i = 0; i < inputSize2; i++) {
					if (!(Character.isDigit(month.charAt(i))) || Integer.valueOf(month) < 0
							|| Integer.valueOf(month) > 12||month.length()>2||month.length()<1) {
						goodInput1 = false;
					}
				}
				if (goodInput1 && goodInput2){// the inputs have the right format
					float totalWeight = 0;
					String[] contents = new String[100];
					boolean flag1 = true;
					boolean flag2 = true;
					boolean flag3 = true;
					int numOfFarms = 0;
					// check whether the file contains missing or error data
					String path =  folderPath+"\\"+ year + "-" + month + ".csv";
					String contentForCheck = "";
					String[] contentsForCheck = new String[100];
					try {
						sc1 = new Scanner(new File(path));

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}

					while (sc1.hasNextLine()) {
						contentForCheck += sc1.nextLine() + ",";
					}
					contentsForCheck = contentForCheck.split(",");
					// check the data in the first column of file
					for (int z = 3; z < contentsForCheck.length; z += 3) {
						if (!contentsForCheck[z].contains(year)) {
							flag1 = false;
							break;
						}
					}
					// check the data in the second column of file
					for (int x = 4; x < contentsForCheck.length; x += 3) {
						if (!contentsForCheck[x].contains("Farm")) {
							flag2 = false;
							break;
						}
					}
					// check the data in the third column of file
					for (int y = 5; y < contentsForCheck.length; y += 3) {
						int size = contentsForCheck[y].length();
						for (int p = 0; p < size; p++) {
							if (!(Character.isDigit(contentsForCheck[y].charAt(p)))) {
								flag3 = false;
								break;
							}
						}
					}

					if (flag1 && flag2 && flag3) {// the files have proper data
						// get the total number of farm participated in producing milk
						try {
							sc2 = new Scanner(new File(path));

						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}

						while (sc2.hasNextLine()) {
							content += sc2.nextLine() + ",";
						}
						contents = content.split(",");

						// get the farms ID of the contents:

						String farmID = "";
						for (int u = 1; u < contents.length; u += 3) {
							if (!contents[u].equals("farm_id")) {
								farmID += contents[u].substring(5, contents[u].length()) + ",";
							}
						}
						// put the farms'ID into array
						String[] farmsIDUnsorted = new String[10000];
						farmsIDUnsorted = farmID.split(",");
						// sort the ID array
						int[] farmsID = new int[farmsIDUnsorted.length];
						for (int index0 = 0; index0 < farmsID.length; index0++) {
							farmsID[index0] = Integer.valueOf(farmsIDUnsorted[index0]);
						}
						Arrays.sort(farmsID);
						// remove duplicate item
						ArrayList<Integer> farmIDs = new ArrayList<Integer>();
						for (int q = 0; q < farmsID.length; q++) {
							if (!farmIDs.contains(farmsID[q]))
								farmIDs.add(farmsID[q]);
						}
						numOfFarms = farmIDs.size();
						// get total weight for each farm this month
						int[] eachTotal = new int[numOfFarms];
						int[] appearTime = new int[numOfFarms];
						for (int r = 0; r < numOfFarms; r++) {
							for (int e = 0; e < contents.length; e++) {
								if (contents[e].equals("Farm " + farmIDs.get(r))) {
									eachTotal[r] += Integer.valueOf(contents[e + 1]);
									appearTime[r] += 1;
								}
							}
						}

						// get the total weight for the specific month
						for (int k = 5; k < contents.length; k += 3) {
							if (Character.isDigit(contents[k].charAt(0))) {
								totalWeight += Integer.valueOf(contents[k]);
							}
						}
						// get each farm's percentage on total weight
						double[] percentage = new double[numOfFarms];
						for (int l = 0; l < numOfFarms; l++) {
							percentage[l] = (eachTotal[l] / totalWeight) * 100;
						}
						data = FXCollections.observableArrayList();
						for (int n = 0; n < numOfFarms; n++) {
							data.addAll(new TableCol("Farm " + String.valueOf(farmIDs.get(n)),
									String.valueOf(String.format("%.2f",percentage[n])) + " %"));
						}
					} else {
						data = FXCollections.observableArrayList(
								new TableCol("Error!", "Please make sure that data in your files are proper."));
					}

					TableColumn firstCol = new TableColumn("Farm ID");
					TableColumn secondCol = new TableColumn("percentage of total weight");
					firstCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("ID"));
					secondCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("totalWeight"));
					table7.setItems(data);
					table7.getColumns().addAll(firstCol, secondCol);
				} else {
					data = FXCollections
							.observableArrayList(new TableCol("Error!", "Make sure your inputs are digit."));
					TableColumn firstCol = new TableColumn("Farm ID");
					TableColumn secondCol = new TableColumn("percentage of total weight");
					firstCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("ID"));
					secondCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("totalWeight"));
					table7.setItems(data);
					table7.getColumns().addAll(firstCol, secondCol);
				}
			}
		});

		// part for date range report
		dateRangeReportLabel = new Label("Date Range Report (Based on date range)");
		table8 = new TableView();
		table8.setEditable(true);
		table9 = new TableView();
		table9.setEditable(true);
		calcButton8 = new Button("Get total weight each farm in the range:");
		calcButton9 = new Button("Get percentage each farm in the range:");
		calcButton8.setOnAction(new EventHandler<ActionEvent>() {
			/*
			 * This method aims to get total milk weight per farm in the date range
			 */
			@Override
			public void handle(ActionEvent event) {
				folderPath = inputFilePath.getText();
				String dateRange1 = startDate.getText();
				String dateRange2 = endDate.getText();
				boolean goodInput1 = true;
				boolean goodInput2 = true;
				boolean goodInput11 = true;
				boolean goodInput12 = true;
				String content = "";
				int numOfFarms = 0;
				int startIndex = 0;
				int endIndex = 0;
				String[] allContents = new String[100000];

				for (int i = 0; i < 4; i++) {
					if (!Character.isDigit(dateRange1.charAt(i))||dateRange1.length()<8||dateRange1.length()>10) {
						goodInput1 = false;
						break;
					}
				}
				for (int i = 0; i < 4; i++) {
					if (!Character.isDigit(dateRange1.charAt(i))) {
						goodInput2 = false;
						break;
					}
				}
				if(dateRange1.length()<8||dateRange1.length()>10) {
					goodInput11=false;
				}
				if(dateRange2.length()<8||dateRange2.length()>10) {
					goodInput12=false;
				}
				if (goodInput1 && goodInput2&&goodInput11&&goodInput12) {// the inputs have the right format
					String startYear = dateRange1.substring(0, 4);
					String endYear = dateRange2.substring(0, 4);
					boolean goodInput3 = false;
					boolean goodInput4 = false;
					int startY = Integer.valueOf(startYear);
					int difference =Integer.valueOf(endYear)-Integer.valueOf(startYear);
					// get the contents of information of the date range
					for(int i = 0;i<difference+1;i++) {
					for (int j = 0; j < 12; j++) {
						String path = folderPath+"\\"+ (startY+i) + "-" + (j + 1) + ".csv";
						try {
							sc2 = new Scanner(new File(path));

						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}

						while (sc2.hasNextLine()) {
							content += sc2.nextLine() + ",";
						}
					}
					}
					allContents = content.split(",");					
					for (int p = 0; p < allContents.length; p++) {
						if (allContents[p].substring(allContents[p].length() - 1, allContents[p].length())
								.equals(dateRange1.substring(dateRange1.length() - 1, dateRange1.length()))
								&& (allContents[p].substring(0, 4).equals(dateRange1.substring(0, 4)))) {
							goodInput3 = true;
							break;
						}
					}
				
					for (int p = 0; p < allContents.length; p++) {
						if (allContents[p].substring(allContents[p].length() - 1, allContents[p].length())
								.equals(dateRange2.substring(dateRange2.length() - 1, dateRange2.length()))
								&& (allContents[p].substring(0, 4).equals(dateRange2.substring(0, 4)))) {
							goodInput4 = true;
							break;
						}
					}
					if (goodInput3 && goodInput4) {// the information during the date range contained in the files
						boolean flag1 = true;
						boolean flag2 = true;
						boolean flag3 = true;
						
						// check whether the start year file contains missing or error data						
							String[] contentsForCheck = new String[100000];
							contentsForCheck = allContents;
							// check the data in the first column of file
						
							// check the data in the second column of file
							for (int x = 1; x < contentsForCheck.length; x += 3) {
								if (!contentsForCheck[x].contains("Farm")&&!contentsForCheck[x].equals("farm_id")) {
									flag2 = false;
									break;
								}
							}
							// check the data in the third column of file
							for (int y = 2; y < contentsForCheck.length; y += 3) {
								int size = contentsForCheck[y].length();
								for (int p = 0; p < size; p++) {
									if (!(Character.isDigit(contentsForCheck[y].charAt(p)))&&!contentsForCheck[y].equals("weight")) {
										flag3 = false;
										break;
									}
								}
								
							}
						if (flag2 && flag3) {// the files have proper data
								
								//check the input date range has the right format
								boolean goodInput6 = false;
								boolean goodInput7 = false;
								for (int o = 0; o < allContents.length; o += 3) {
									if(allContents[o].equals(dateRange1))									
										goodInput6 = true;								
									}
							
								for (int o = 0; o < allContents.length; o += 3) {
									if(allContents[o].equals(dateRange2))																		
										goodInput7 = true;
										
									}
								// get the farms ID of the contents
								String FarmID = "";
								for (int u = 1; u < allContents.length; u += 3) {
									if (!allContents[u].equals("farm_id")) {
										FarmID += allContents[u].substring(5, allContents[u].length()) + ",";
									}
								}
								// put the farms'ID into array
								String[] farmsIDUnsorted = new String[10000];
								farmsIDUnsorted = FarmID.split(",");
								// sort the ID array
								int[] farmsID = new int[farmsIDUnsorted.length];
								for (int index0 = 0; index0 < farmsID.length; index0++) {
									farmsID[index0] = Integer.valueOf(farmsIDUnsorted[index0]);
								}
								Arrays.sort(farmsID);
								// remove duplicate item
								ArrayList<Integer> farmIDList = new ArrayList<Integer>();
								for (int q = 0; q < farmsID.length; q++) {
									if (!farmIDList.contains(farmsID[q]))
										farmIDList.add(farmsID[q]);
								}
								numOfFarms = farmIDList.size();
								String[] rangeContents = new String[100000];
								// get the information of the given range
				
								if(goodInput6&&goodInput7) {
									int start=0;
									int end=0;								
									for (int o = 0; o < allContents.length; o++) {
										if(allContents[o].equals(dateRange1)) {									
											start = o;	
											break;
										}
										}																														
									for (int o = 0; o < allContents.length; o++) {
										if(allContents[o].equals(dateRange2))									
											end = o;																						
										}
								int index=0;
								for (int l = start; l < end + 3; l++) {
									rangeContents[index] = allContents[l];
									index++;
								}					
								// get the total weight for each farm during the date range
								double[] totalWeight = new double[numOfFarms];
								for (int q = 0; q < numOfFarms; q++) {
									for (int j = 0; j < rangeContents.length; j++) {
										if (rangeContents[j] == null) {
											break;
										}
										if (rangeContents[j].equals("Farm " + farmIDList.get(q))) {
											totalWeight[q] += (double) Integer.valueOf(rangeContents[j + 1]);
										}
									}
								}

								data = FXCollections.observableArrayList();
								for (int n = 0; n < totalWeight.length ; n++) {
									data.addAll(new TableCol(String.valueOf(farmIDList.get(n)),
											String.valueOf(totalWeight[n])));
								}
								}else {
									data = FXCollections.observableArrayList(
											new TableCol("Your entered date range should be", " the format of the first column of your provided files."));
								}
							 
					}else {
						data = FXCollections.observableArrayList(
								new TableCol("Error!", "Please make sure that data in your files are proper55."));
						}
					}else {
							data = FXCollections.observableArrayList(
									new TableCol("Error!", "Please make sure that data in your files are proper."));
						}
						TableColumn firstCol = new TableColumn("Farm ID");
						TableColumn secondCol = new TableColumn("Total weight during this range");
						firstCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("ID"));
						secondCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("totalWeight"));
						table8.setItems(data);
						table8.getColumns().addAll(firstCol, secondCol);
					} else {
						data = FXCollections.observableArrayList(
								new TableCol("Error!", "Make sure your inputs have the right format."));
						TableColumn firstCol = new TableColumn("Farm ID");
						TableColumn secondCol = new TableColumn("totalWeight");
						firstCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("ID"));
						secondCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("totalWeight"));
						table8.setItems(data);
						table8.getColumns().addAll(firstCol, secondCol);
					}

			}

		});
		calcButton9.setOnAction(new EventHandler<ActionEvent>() {
			/*
			 * This method aims to get percentage on total weight for each farm during the date range
			 */
			@Override
			public void handle(ActionEvent event) {
				folderPath = inputFilePath.getText();
				String dateRange1 = startDate.getText();
				String dateRange2 = endDate.getText();
				boolean goodInput1 = true;
				boolean goodInput2 = true;
				boolean goodInput11 = true;
				boolean goodInput12 = true;
				String content = "";
				int numOfFarms = 0;
				int startIndex = 0;
				int endIndex = 0;
				String[] allContents = new String[100000];

				for (int i = 0; i < 4; i++) {
					if (!Character.isDigit(dateRange1.charAt(i))||dateRange1.length()<8||dateRange1.length()>10) {
						goodInput1 = false;
						break;
					}
				}
				for (int i = 0; i < 4; i++) {
					if (!Character.isDigit(dateRange1.charAt(i))) {
						goodInput2 = false;
						break;
					}
				}
				if(dateRange1.length()<8||dateRange1.length()>10) {
					goodInput11=false;
				}
				if(dateRange2.length()<8||dateRange2.length()>10) {
					goodInput12=false;
				}
				if (goodInput1 && goodInput2&&goodInput11&&goodInput12) {// the inputs have the right format
					String startYear = dateRange1.substring(0, 4);
					String endYear = dateRange2.substring(0, 4);
					boolean goodInput3 = false;
					boolean goodInput4 = false;
					int startY = Integer.valueOf(startYear);
					int difference =Integer.valueOf(endYear)-Integer.valueOf(startYear);
					// get the contents of information of the date range
					for(int i = 0;i<difference+1;i++) {
					for (int j = 0; j < 12; j++) {
						String path = folderPath+"\\"+ (startY+i) + "-" + (j + 1) + ".csv";
						try {
							sc2 = new Scanner(new File(path));

						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}

						while (sc2.hasNextLine()) {
							content += sc2.nextLine() + ",";
						}
					}
					}
					allContents = content.split(",");					
					for (int p = 0; p < allContents.length; p++) {
						if (allContents[p].substring(allContents[p].length() - 1, allContents[p].length())
								.equals(dateRange1.substring(dateRange1.length() - 1, dateRange1.length()))
								&& (allContents[p].substring(0, 4).equals(dateRange1.substring(0, 4)))) {
							goodInput3 = true;
							break;
						}
					}
				
					for (int p = 0; p < allContents.length; p++) {
						if (allContents[p].substring(allContents[p].length() - 1, allContents[p].length())
								.equals(dateRange2.substring(dateRange2.length() - 1, dateRange2.length()))
								&& (allContents[p].substring(0, 4).equals(dateRange2.substring(0, 4)))) {
							goodInput4 = true;
							break;
						}
					}
					if (goodInput3 && goodInput4) {//the information during the date range contained in the files
						boolean flag1 = true;
						boolean flag2 = true;
						boolean flag3 = true;
						
						// check whether the start year file contains missing or error data
						
							String[] contentsForCheck = new String[100000];
							contentsForCheck = allContents;
							// check the data in the first column of file
						
							// check the data in the second column of file
							for (int x = 1; x < contentsForCheck.length; x += 3) {
								if (!contentsForCheck[x].contains("Farm")&&!contentsForCheck[x].equals("farm_id")) {
									flag2 = false;
									break;
								}
							}
							// check the data in the third column of file
							for (int y = 2; y < contentsForCheck.length; y += 3) {
								int size = contentsForCheck[y].length();
								for (int p = 0; p < size; p++) {
									if (!(Character.isDigit(contentsForCheck[y].charAt(p)))&&!contentsForCheck[y].equals("weight")) {
										flag3 = false;
										break;
									}
								}
								
							}
						if (flag2 && flag3) {// the files have proper data
								
								//check the input date range has the right format
								boolean goodInput6 = false;
								boolean goodInput7 = false;
								for (int o = 0; o < allContents.length; o += 3) {
									if(allContents[o].equals(dateRange1))									
										goodInput6 = true;								
									}
							
								for (int o = 0; o < allContents.length; o += 3) {
									if(allContents[o].equals(dateRange2))																		
										goodInput7 = true;
										
									}
								// get the farms ID of the contents
								String FarmID = "";
								for (int u = 1; u < allContents.length; u += 3) {
									if (!allContents[u].equals("farm_id")) {
										FarmID += allContents[u].substring(5, allContents[u].length()) + ",";
									}
								}
								// put the farms'ID into array
								String[] farmsIDUnsorted = new String[10000];
								farmsIDUnsorted = FarmID.split(",");
								// sort the ID array
								int[] farmsID = new int[farmsIDUnsorted.length];
								for (int index0 = 0; index0 < farmsID.length; index0++) {
									farmsID[index0] = Integer.valueOf(farmsIDUnsorted[index0]);
								}
								Arrays.sort(farmsID);
								// remove duplicate item
								ArrayList<Integer> farmIDList = new ArrayList<Integer>();
								for (int q = 0; q < farmsID.length; q++) {
									if (!farmIDList.contains(farmsID[q]))
										farmIDList.add(farmsID[q]);
								}
								numOfFarms = farmIDList.size();
								String[] rangeContents = new String[100000];
								// get the information of the given range
				
								if(goodInput6&&goodInput7) {
									int start=0;
									int end=0;								
									for (int o = 0; o < allContents.length; o++) {
										if(allContents[o].equals(dateRange1)) {									
											start = o;	
											break;
										}
										}																														
									for (int o = 0; o < allContents.length; o++) {
										if(allContents[o].equals(dateRange2))									
											end = o;																						
										}
								int index=0;
								for (int l = start; l < end + 3; l++) {
									rangeContents[index] = allContents[l];
									index++;
								}					
								// get the total weight for each farm during the date range
								double[] totalWeight = new double[numOfFarms];
								for (int q = 0; q < numOfFarms; q++) {
									for (int j = 0; j < rangeContents.length; j++) {
										if (rangeContents[j] == null) {
											break;
										}
										if (rangeContents[j].equals("Farm " + farmIDList.get(q))) {
											totalWeight[q] += (double) Integer.valueOf(rangeContents[j + 1]);
										}
									}
								}
								//get the percentage for each farm
								double total=0.0;
								for(int i=0;i<totalWeight.length;i++) {
									total+=totalWeight[i];
								}
								double[] percentage = new double[numOfFarms];
								for (int p = 0; p < numOfFarms; p++) {
									percentage[p] = (totalWeight[p] / total) * 100;
								}

								data = FXCollections.observableArrayList();
								for (int n = 0; n < percentage.length ; n++) {
									data.addAll(new TableCol(String.valueOf(farmIDList.get(n)),
											String.valueOf(String.format("%.2f", percentage[n]))+"%"));
								}
								}else {
									data = FXCollections.observableArrayList(
											new TableCol("Your entered date range should be", " the format of the first column of your provided files."));
								}							 
					}else {
						data = FXCollections.observableArrayList(
								new TableCol("Error!", "Please make sure that data in your files are proper55."));
						}
					}else {
							data = FXCollections.observableArrayList(
									new TableCol("Error!", "Please make sure that data in your files are proper."));
						}
						TableColumn firstCol = new TableColumn("Farm ID");
						TableColumn secondCol = new TableColumn("Total weight during this range");
						firstCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("ID"));
						secondCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("totalWeight"));
						table9.setItems(data);
						table9.getColumns().addAll(firstCol, secondCol);
					} else {
						data = FXCollections.observableArrayList(
								new TableCol("Error!", "Make sure your inputs have the right format."));
						TableColumn firstCol = new TableColumn("Farm ID");
						TableColumn secondCol = new TableColumn("totalWeight");
						firstCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("ID"));
						secondCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("totalWeight"));
						table9.setItems(data);
						table9.getColumns().addAll(firstCol, secondCol);
					}

			}

		});
		

		// get min/max/average milk weight of specific farm per month in a specific year
		calcButton5 = new Button("Get min/max/average per month(Based on Farm ID and year):");
		table5 = new TableView();
		table5.setEditable(true);
		calcButton5.setOnAction(new EventHandler<ActionEvent>() {
			/*
			 * Method is automatically called when an event occurs (e.g, button is pressed)
			 * This method aims to get the total weight per month of this farm
			 */
			@Override
			public void handle(ActionEvent event) {
				folderPath = inputFilePath.getText();
				String year = inputYear.getText();
				String ID = inputID.getText();
				boolean goodInput1 = true;
				boolean goodInput2 = true;
				int size1 = year.length();
				int size2 = ID.length();
				int numOfFarms = 0;
				String content = "";
				int monthWeight = 0;
				for (int i = 0; i < size1; i++) {
					if (!(Character.isDigit(year.charAt(i))) || Integer.valueOf(year) > 2020
							|| Integer.valueOf(year) < 0||year.length()<4||year.length()>4) {
						goodInput1 = false;
					}
				}
				for (int i = 0; i < size2; i++) {
					if (!(Character.isDigit(ID.charAt(i))) || Integer.valueOf(ID) < 0||ID.length()<1||ID.length()>4) {
						goodInput2 = false;
					}
				}
				if (goodInput1 && goodInput2) {// the inputs have the right format
					boolean flag1 = true;
					boolean flag2 = true;
					boolean flag3 = true;
					Integer January = 0, February = 0, March = 0, April = 0, May = 0, June = 0, July = 0, August = 0,
							September = 0, October = 0, November = 0, December = 0;
					Integer[] IntegerM = new Integer[] { January, February, March, April, May, June, July, August,
							September, October, November, December };
					String[] contents = new String[100000];
					// check whether the file contains missing or error data
					for (int i = 0; i < 12; i++) {
						String path = folderPath+"\\"+ year + "-" + (i + 1) + ".csv";
						String contentForCheck = "";
						String[] contentsForCheck = new String[100000];
						try {
							sc1 = new Scanner(new File(path));

						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}

						while (sc1.hasNextLine()) {
							contentForCheck += sc1.nextLine() + ",";
						}
						contentsForCheck = contentForCheck.split(",");
						// check the data in the first column of file
						for (int z = 3; z < contentsForCheck.length; z += 3) {
							if (!contentsForCheck[z].contains(year)) {
								flag1 = false;
								break;
							}
						}
						// check the data in the second column of file
						for (int x = 4; x < contentsForCheck.length; x += 3) {
							if (!contentsForCheck[x].contains("Farm")) {
								flag2 = false;
								break;
							}
						}
						// check the data in the third column of file
						for (int y = 5; y < contentsForCheck.length; y += 3) {
							int size = contentsForCheck[y].length();
							for (int p = 0; p < size; p++) {
								if (!(Character.isDigit(contentsForCheck[y].charAt(p)))) {

									flag3 = false;
									break;
								}

							}
						}
					}
					// get one file for get the number of farms
					String path = folderPath+"\\"+ year + "-" + 1 + ".csv";

					try {
						sc2 = new Scanner(new File(path));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}

					while (sc2.hasNextLine()) {
						content += sc2.nextLine() + ",";
					}
					contents = content.split(",");

					// get the farms ID of the contents
					String farmID = "";
					for (int u = 1; u < contents.length; u += 3) {
						if (!contents[u].equals("farm_id")) {
							farmID += contents[u].substring(5, contents[u].length()) + ",";
						}
					}
					// put the farms'ID into array
					String[] farmsIDUnsorted = new String[10000];
					farmsIDUnsorted = farmID.split(",");
					// sort the ID array
					int[] farmsID = new int[farmsIDUnsorted.length];
					for (int index0 = 0; index0 < farmsID.length; index0++) {
						farmsID[index0] = Integer.valueOf(farmsIDUnsorted[index0]);
					}
					Arrays.sort(farmsID);
					// remove duplicate item
					ArrayList<Integer> farmIDs = new ArrayList<Integer>();
					for (int q = 0; q < farmsID.length; q++) {
						if (!farmIDs.contains(farmsID[q]))
							farmIDs.add(farmsID[q]);
					}
					numOfFarms = farmIDs.size();
					// check whether the information of the input farm ID is contained in the file
					boolean flag4 = false;
					for (int y = 0; y < farmIDs.size(); y++) {
						if (String.valueOf(farmIDs.get(y)).equals(ID)) {
							flag4 = true;
							break;
						}
					}
					if (flag4) {//the farm's information contained in the files
						if (flag1 && flag2 && flag3) {// the files have proper data
							content = "";
							contents = new String[contents.length];
							int minWeight = 0;
							int maxWeight = 0;
							String averageWeight = "";
							String[] minWeights = new String[12];
							String[] maxWeights = new String[12];
							int[] monthWeights = new int[contents.length];
							String[] avgWeights = new String[12];
							String[] storeWeights = new String[contents.length];
							for (int i = 0; i < 12; i++) {
								String path1 = folderPath+"\\"+ year + "-" + (i + 1) + ".csv";
								// get the contents
								try {
									sc2 = new Scanner(new File(path1));

								} catch (FileNotFoundException e) {
									e.printStackTrace();
								}

								while (sc2.hasNextLine()) {
									content += sc2.nextLine() + ",";
								}
								contents = content.split(",");
								// get the minimum milk weight for the farm in each month							
								int appearTime = 0;// get the times ID appear in the file of this month
								for (int q = 0; q < contents.length; q++) {
									if (contents[q].equals("Farm " + ID)) {
										appearTime += 1;
									}
								}
								// put the milk weights in the month of this farm into array
								int index1 = 0;
								for (int p = 0; p < contents.length; p++) {
									if (contents[p].equals("Farm " + ID)) {
										storeWeights[index1++] = contents[p + 1];
									}
								}
								// get the minimum weight of this month
								minWeight = Integer.valueOf((storeWeights[0]));
								for (int u = 1; u < appearTime; u++) {
									if (Integer.valueOf(storeWeights[u]) < minWeight) {
										minWeight = Integer.parseInt(storeWeights[u]);
									}
								}
								minWeights[i] = String.valueOf(minWeight);
								minWeight = 0;
								// get the maximum weight of this month
								maxWeight = Integer.valueOf((storeWeights[0]));
								for (int u = 1; u < appearTime; u++) {
									if (Integer.valueOf(storeWeights[u]) > maxWeight) {
										maxWeight = Integer.parseInt(storeWeights[u]);
									}
								}
								maxWeights[i] = String.valueOf(maxWeight);
								maxWeight = 0;

								// get the average milk weight for the farm in each month

								// get the total milk weight for the farm in each month
								for (int p = 0; p < contents.length; p++) {
									if (contents[p].equals("Farm " + ID)) {
										monthWeights[i] += Integer.valueOf(contents[p + 1]);
									}
								}
								content = "";
								contents = new String[contents.length];
								// put in the average weight into array
								avgWeights[i] = String.valueOf((Integer.valueOf(monthWeights[i]) / appearTime));
							}
							data = FXCollections.observableArrayList();
							for (int index = 0; index < minWeights.length; index++) {
								data.add(new TableCol(String.valueOf(index + 1),
										minWeights[index] + "/" + maxWeights[index] + "/" + avgWeights[index]));
							}
						} else {
							data = FXCollections.observableArrayList(
									new TableCol("Error!", "Please make sure that data in your files are proper."));
						}
					} else {
						data = FXCollections.observableArrayList(new TableCol("Error!",
								"Please make sure that you enter the farm ID that is in files."));
					}

					TableColumn firstCol = new TableColumn("#Month");
					TableColumn secondCol = new TableColumn("min/max/avg");
					firstCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("ID"));
					secondCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("totalWeight"));
					table5.setItems(data);
					table5.getColumns().addAll(firstCol, secondCol);
				} else {
					data = FXCollections
							.observableArrayList(new TableCol("Error!", "Make sure your inputs are digit."));
					TableColumn firstCol = new TableColumn("#Month");
					TableColumn secondCol = new TableColumn("totalWeight");
					firstCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("ID"));
					secondCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("totalWeight"));
					table5.setItems(data);
					table5.getColumns().addAll(firstCol, secondCol);
				}

			}
		});

		// get min/max/average milk weight of a specific month for all farms
		calcButton10 = new Button("Get farms min/max/average in a month(Based on month and year):");
		table10 = new TableView();
		table10.setEditable(true);
		calcButton10.setOnAction(new EventHandler<ActionEvent>() {
			/*
			 * Method is automatically called when an event occurs (e.g, button is pressed)
			 * This method aims to allow the user to display min, max, average, for all
			 * farms for user-specified month and year
			 */
			@Override
			public void handle(ActionEvent event) {
				folderPath = inputFilePath.getText();
				String year = inputYear.getText();
				String month = inputMonth.getText();
				boolean goodInput1 = true;
				boolean goodInput2 = true;
				int size1 = year.length();
				int size2 = month.length();
				int numOfFarms = 0;
				String content = "";
				int monthWeight = 0;
				// check the input is valid number
				for (int i = 0; i < size1; i++) {
					if (!(Character.isDigit(year.charAt(i))) || Integer.valueOf(year) > 2020
							|| Integer.valueOf(year) < 0 || year.equals(null)||year.length()<4||year.length()>4) {
						goodInput1 = false;
					}

				}
				for (int i = 0; i < size2; i++) {
					if (!(Character.isDigit(month.charAt(i))) || Integer.valueOf(month) > 12
							|| Integer.valueOf(month) < 1 || month.equals(null)||month.length()<1||month.length()>3) {
						goodInput2 = false;
					}
				}
				if (goodInput1 && goodInput2) {// the inputs have the right format
					boolean flag1 = true;
					boolean flag2 = true;
					boolean flag3 = true;
					Integer January = 0, February = 0, March = 0, April = 0, May = 0, June = 0, July = 0, August = 0,
							September = 0, October = 0, November = 0, December = 0;
					Integer[] IntegerM = new Integer[] { January, February, March, April, May, June, July, August,
							September, October, November, December };
					String[] contents = new String[1000000];
					// check whether the file contains missing or error data
					String path = folderPath + "\\"+ year + "-" + month + ".csv";
					String contentForCheck = "";
					String[] contentsForCheck = new String[1000000];
					try {
						sc1 = new Scanner(new File(path));

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}

					while (sc1.hasNextLine()) {
						contentForCheck += sc1.nextLine() + ",";
					}
					contentsForCheck = contentForCheck.split(",");
					// check the data in the first column of file
					for (int z = 3; z < contentsForCheck.length; z += 3) {
						if (!contentsForCheck[z].contains(year)) {
							flag1 = false;
							break;
						}
					}
					// check the data in the second column of file
					for (int x = 4; x < contentsForCheck.length; x += 3) {
						if (!contentsForCheck[x].contains("Farm")) {
							flag2 = false;
							break;
						}
					}
					// check the data in the third column of file
					for (int y = 5; y < contentsForCheck.length; y += 3) {
						int size = contentsForCheck[y].length();
						for (int p = 0; p < size; p++) {
							if (!(Character.isDigit(contentsForCheck[y].charAt(p)))) {
								flag3 = false;
								break;
							}
						}
					}				
					try {
						sc2 = new Scanner(new File(path));

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}

					while (sc2.hasNextLine()) {
						content += sc2.nextLine() + ",";
					}
					contents = content.split(",");

					// get the largest farm ID
					int largestID = Integer.valueOf(contents[4].substring(5, contents[4].length()));
					for (int index = 7; index < contents.length; index += 3) {
						if (Integer.valueOf(contents[index].substring(5, contents[index].length())) > largestID)
							largestID = Integer.valueOf(contents[index].substring(5, contents[index].length()));
					}
					// get the farms ID of the contents
					String farmID = "";
					for (int u = 1; u < contents.length; u += 3) {
						if (!contents[u].equals("farm_id")) {
							farmID += contents[u].substring(5, contents[u].length()) + ",";
						}
					}
					// put the farms'ID into array
					String[] farmsIDUnsorted = new String[1000000];
					farmsIDUnsorted = farmID.split(",");
					// sort the ID array
					int[] farmsID = new int[farmsIDUnsorted.length];
					for (int index0 = 0; index0 < farmsID.length; index0++) {
						farmsID[index0] = Integer.valueOf(farmsIDUnsorted[index0]);
					}
					Arrays.sort(farmsID);
					// remove duplicate item
					ArrayList<Integer> farmIDs = new ArrayList<Integer>();
					for (int q = 0; q < farmsID.length; q++) {
						if (!farmIDs.contains(farmsID[q]))
							farmIDs.add(farmsID[q]);
					}
					numOfFarms = farmIDs.size();

					if (flag1 && flag2 && flag3) {// the files have proper data
						String minWeight = "";
						String maxWeight = "";
						String averageWeight = "";
						String[] minWeights = new String[numOfFarms];
						String[] maxWeights = new String[numOfFarms];
						double[] avgWeights = new double[numOfFarms];

						// get the minimum weight for each farm in a specific month
						minWeight = "1000000000";

						for (int l = 0; l < numOfFarms; l++) {
							for (int p = 0; p < contents.length; p++) {
								if (contents[p].equals("Farm " + farmIDs.get(l))
										&& Integer.valueOf(contents[p + 1]) < Integer.valueOf(minWeight)) {
									minWeight = contents[p + 1];
								}
							}
							minWeights[l] = minWeight;
							minWeight = "10000000";
						}
						// get the maximum weight for each farm in a specific month
						maxWeight = "0";
						for (int l = 0; l < numOfFarms; l++) {
							for (int p = 0; p < contents.length; p++) {
								if (contents[p].equals("Farm " + farmIDs.get(l))
										&& Integer.valueOf(contents[p + 1]) > Integer.valueOf(maxWeight)) {
									maxWeight = contents[p + 1];
								}
							}
							maxWeights[l] = maxWeight;
							maxWeight = "0";
						}
						// get the average weight for each farm in a specific month
						int[] eachTotal = new int[numOfFarms];
						int[] appearTime = new int[numOfFarms];
						for (int r = 0; r < numOfFarms; r++) {
							for (int e = 0; e < contents.length; e++) {
								if (contents[e].equals("Farm " + farmIDs.get(r))) {
									eachTotal[r] += Integer.valueOf(contents[e + 1]);
									appearTime[r] += 1;
								}
							}
						}
						for (int k = 0; k < numOfFarms; k++) {
							avgWeights[k] = eachTotal[k] / appearTime[k];
						}
						data = FXCollections.observableArrayList();
						for (int o = 0; o < farmIDs.size(); o++) {
							data.add(new TableCol("Farm " + farmIDs.get(o),
									minWeights[o] + "/" + maxWeights[o] + "/" + avgWeights[o]));
						}
					} else {
						data = FXCollections.observableArrayList(
								new TableCol("Error!", "Please make sure that data in your files are proper."));
					}

					TableColumn firstCol = new TableColumn("#Month");
					TableColumn secondCol = new TableColumn("min/max/avg");
					firstCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("ID"));
					secondCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("totalWeight"));
					table10.setItems(data);
					table10.getColumns().addAll(firstCol, secondCol);
				} else {
					data = FXCollections
							.observableArrayList(new TableCol("Error!", "Make sure your inputs are digit."));
					TableColumn firstCol = new TableColumn("#Month");
					TableColumn secondCol = new TableColumn("totalWeight");
					firstCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("ID"));
					secondCol.setCellValueFactory(new PropertyValueFactory<TableColumn, String>("totalWeight"));
					table10.setItems(data);
					table10.getColumns().addAll(firstCol, secondCol);
				}

			}
		});
		gridPane.add(label, 0, 0); 
		gridPane.add(warn, 1, 0);
		gridPane.add(file, 2, 0);
		gridPane.add(inputFilePath, 3, 0);
		gridPane.add(year, 0, 1); 
		gridPane.add(dateRange1, 0, 2); 
		gridPane.add(dateRange2, 2, 2);
		gridPane.add(month, 4, 1);
		gridPane.add(farmID, 2, 1);
		gridPane.add(inputYear, 1, 1);
		gridPane.add(inputID, 3, 1);
		gridPane.add(inputMonth, 5, 1);
		gridPane.add(startDate, 1, 2);
		gridPane.add(endDate, 3, 2);
		gridPane.add(calcButton11, 5, 2);

		gridPane.add(farmReportLabel, 0, 5);
		gridPane.add(calcButton1, 0, 6);
		gridPane.add(calcButton2, 1, 6);
		gridPane.add(table1, 0, 7); 
		gridPane.add(table2, 1, 7); 

		gridPane.add(annualReportLabel, 3, 5);
		gridPane.add(calcButton3, 3, 6);
		gridPane.add(calcButton4, 4, 6);

		gridPane.add(table3, 3, 7); 
		gridPane.add(table4, 4, 7);

		gridPane.add(monthlyReportLabel, 0, 9);
		gridPane.add(calcButton6, 0, 10);
		gridPane.add(calcButton7, 1, 10);
		gridPane.add(table6, 0, 11); 
		gridPane.add(table7, 1, 11); 

		gridPane.add(dateRangeReportLabel, 3, 9);
		gridPane.add(calcButton8, 3, 10);
		gridPane.add(calcButton9, 4, 10);
		gridPane.add(table8, 3, 11); 
		gridPane.add(table9, 4, 11); 

		gridPane.add(calcButton5, 5, 6);
		gridPane.add(calcButton10, 5, 10);
		gridPane.add(table5, 5, 7); 
		gridPane.add(table10, 5, 11); 

		scene = new Scene(gridPane); // Create scene containing the board pane
		applicationStage.setScene(scene); // Set window's scene
		applicationStage.setTitle("Milk Weights Calculation Made By Zhan Yu"); // Set window's title
		applicationStage.show(); // Display window
	}

	public static void main(String[] args) {
		launch(args); // Launch application

	}
}
