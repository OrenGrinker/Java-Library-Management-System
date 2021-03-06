<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.geometry.Insets?>
<?import javafx.scene.control.Button?>
<?import javafx.scene.control.DatePicker?>
<?import javafx.scene.control.Menu?>
<?import javafx.scene.control.MenuBar?>
<?import javafx.scene.control.MenuButton?>
<?import javafx.scene.control.MenuItem?>
<?import javafx.scene.control.TextField?>
<?import javafx.scene.image.Image?>
<?import javafx.scene.image.ImageView?>
<?import javafx.scene.layout.HBox?>
<?import javafx.scene.layout.VBox?>
<?import javafx.scene.text.Font?>
<?import javafx.scene.text.Text?>

<VBox prefHeight="519.0" prefWidth="440.0" spacing="10.0" style="-fx-background-color: white;" xmlns="http://javafx.com/javafx/9.0.1" xmlns:fx="http://javafx.com/fxml/1" fx:controller="controllers.AAB_FXML_AddABook_Controller">
   <children>
      <MenuBar fx:id="menuBar" style="-fx-background-color: white;">
         <menus>
            <Menu mnemonicParsing="false" text="Profile">
               <items>
                  <MenuItem fx:id="myProfileMenuItem" mnemonicParsing="false" onAction="#myProfileMenuItemClicked" text="My Profile" />
                  <Menu mnemonicParsing="false" text="Student Profile">
                     <items>
                        <MenuItem fx:id="createNewMenuItem" mnemonicParsing="false" onAction="#createNewMenuItemClicked" text="Create New Profile" />
                        <MenuItem fx:id="viewProfileMenuItem" mnemonicParsing="false" onAction="#viewProfileMenuItemClicked" text="View Profile" />
                     </items>
                  </Menu>
                  <MenuItem fx:id="signOutMenuItem" mnemonicParsing="false" onAction="#signOutMenuItemClicked" text="Sign Out" />
               </items>
            </Menu>
            <Menu mnemonicParsing="false" text="Book Management">
               <items>
                  <MenuItem fx:id="SearchABookMenuItem" mnemonicParsing="false" onAction="#SearchABookMenuItemClicked" text="Search" />
                  <MenuItem fx:id="returnABookMenuItem" mnemonicParsing="false" onAction="#returnABookMenuItemClicked" text="Return A Book" />
                  <MenuItem fx:id="stockManagmentMenuItem" mnemonicParsing="false" onAction="#stockManagmentMenuItemClicked" text="Stock Managment" />
               </items>
            </Menu>
            <Menu mnemonicParsing="false" text="Help">
               <items>
                  <MenuItem fx:id="AboutMenuItem" mnemonicParsing="false" onAction="#AboutMenuItemClicked" text="About" />
               </items>
            </Menu>
         </menus>
         <VBox.margin>
            <Insets left="-15.0" />
         </VBox.margin>
      </MenuBar>
      <Text strokeType="OUTSIDE" strokeWidth="0.0" text="Add A Book">
         <font>
            <Font name="Calibri Bold" size="48.0" />
         </font>
      </Text>
      <HBox spacing="10.0">
         <children>
            <VBox spacing="10.0">
               <children>
                  <Text strokeType="OUTSIDE" strokeWidth="0.0" text="Fill fields" wrappingWidth="198.13665962219238">
                     <font>
                        <Font size="17.0" />
                     </font>
                  </Text>
                  <TextField fx:id="SerialNumberTextField" promptText="*Serial Number" />
                  <TextField fx:id="NameTextField" promptText="*Name" />
                  <TextField fx:id="AuthoTextField" promptText="*Author" />
                  <TextField fx:id="ShelfTextField" promptText="*Shelf" />
                  <TextField fx:id="CopiesNumberTextField" promptText="*Number Of Copies" />
                  <TextField fx:id="EditionTextField" promptText="*Edition" />
                  <DatePicker fx:id="PrinitngDateDatePicker" prefWidth="200.0" promptText="*Prinitng Date" />
                  <DatePicker fx:id="AquisitionDateTextField" prefWidth="200.0" promptText="*Purchased Date " />
                  <TextField fx:id="DescriptionTextFiel" promptText="Description" />
                  <TextField fx:id="pdfPathTextField" disable="true" promptText="Table Of Content PDF Path" />
               </children>
            </VBox>
            <VBox alignment="TOP_CENTER">
               <children>
                  <VBox spacing="10.0">
                     <children>
                        <Text strokeType="OUTSIDE" strokeWidth="0.0" text="Select Subjects" textAlignment="CENTER" wrappingWidth="198.13665962219238">
                           <font>
                              <Font size="17.0" />
                           </font>
                        </Text>
                        <MenuButton fx:id="chooseMenuButton" mnemonicParsing="false" prefHeight="25.0" prefWidth="200.0" text="Choose">
                           <items>
                              <MenuItem fx:id="softwareMenuItem" mnemonicParsing="false" onAction="#softwareMenuItemClicked" text="Software" />
                              <MenuItem fx:id="InforamationSystemsMenuItem" mnemonicParsing="false" onAction="#InforamationSystemsMenuItemClicked" text="Inforamation Systems" />
                              <MenuItem fx:id="industryNManagmenMenuItem" mnemonicParsing="false" onAction="#industryNmanagmentMenuItemClicked" text="Industry &amp; managment" />
                              <MenuItem fx:id="eclectricityNEkctronicsMenuItem" mnemonicParsing="false" onAction="#eclectricityNEkctronicsMenuItemClicked" text="Electronics" />
                              <MenuItem fx:id="AppliedMathematicsMenuItem" mnemonicParsing="false" onAction="#AppliedMathematicsMenuItemClicked" text="Applied Mathematics" />
                              <MenuItem fx:id="BiotechnologyMenuItem" mnemonicParsing="false" onAction="#BiotechnologyMenuItemClicked" text="Biotechnology" />
                              <MenuItem fx:id="OpticsMenuItem" mnemonicParsing="false" onAction="#OpticsMenuItemClicked" text="Optics" />
                           </items>
                        </MenuButton>
                     </children>
                  </VBox>
                  <ImageView fitHeight="150.0" fitWidth="200.0" pickOnBounds="true" preserveRatio="true">
                     <image>
                        <Image url="@../z_images/BookCartoon.jpg" />
                     </image>
                  </ImageView>
                  <Button fx:id="addPDFButton" mnemonicParsing="false" onAction="#addPDFButtonClicked" prefWidth="200.0" text="Select PDF File">
                     <VBox.margin>
                        <Insets top="142.0" />
                     </VBox.margin>
                  </Button>
               </children>
            </VBox>
         </children>
      </HBox>
      <HBox spacing="10.0">
         <children>
            <Button fx:id="backButton" mnemonicParsing="false" onAction="#backButtonClicked" prefWidth="200.0" text="Back" />
            <Button fx:id="saveButton" mnemonicParsing="false" onAction="#saveButtonClicked" prefWidth="200.0" text="Save" />
         </children>
      </HBox>
   </children>
   <padding>
      <Insets bottom="15.0" left="15.0" right="15.0" />
   </padding>
</VBox>
