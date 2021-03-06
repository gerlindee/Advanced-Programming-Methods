package GUI;

import ADTs.MyDictionary;
import ADTs.MyList;
import ADTs.MyStack;
import ADTs.MyTuple;
import Heap.Heap;
import Model.Expressions.*;
import Model.ProgramState;
import Model.Statements.*;
import Repository.Repository;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import Controller.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;

public class GUI extends Application {

    private MyList<IStatement> statements;
    private List<ProgramState> programs;
    private MyList<Controller> controllers;
    private Controller current_controller;
    private ProgramState main_thread;
    private ProgramState last_step;

    private Stage primaryStage;
    private Scene ProgramsScene;
    private Scene MenuScene;

    private Label menuLabel;
    private VBox menuLayout;
    private ListView<String> selectProgram;
    private Button choose_button;

    private ComboBox<String> thread_choice_box;
    private HBox thread_selection;
    private TextArea thread_text;
    private Button cancel_button;
    private Button step_button;

    private TableView<HeapEntry> heap; // same as for symTable
    private ListView<Integer> out;
    private TableView<FileTableEntry> fileTable; // same as for symTable
    private TableView<SymTableEntry> symTable; // use this new class rather than MyTuple<stuff> (like in ProgramState) because it's easier to deal with the TableColumns settings
    private ListView<String> stack;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        this.primaryStage = new Stage();
        this.primaryStage.setTitle("Toy Language");

        this.buttons();

        this.createMenu();
        this.createThreadsChoiceBox();

        this.thread_text = new TextArea();
        this.threadListUpdateListener();

        // buttons for the program screen
        HBox buttons2 = new HBox();
        Pane spacer = new Pane();
        spacer.setMinSize(15, 3);
        HBox.setHgrow(spacer, Priority.ALWAYS);
        buttons2.getChildren().add(this.cancel_button);
        buttons2.getChildren().add(spacer);
        buttons2.getChildren().add(this.step_button);

        VBox previewArea = new VBox();
        TextArea preview = new TextArea();
        preview.setPromptText("NO PROGRAMS.");
        previewArea.setAlignment(Pos.TOP_CENTER);
        previewArea.getChildren().add(preview);
        previewArea.minHeight(200);

        // grid for the program details
        BorderPane layoutProgram = new BorderPane();
        BorderPane.setMargin(this.thread_text, new Insets(7, 0, 7, 0));
        layoutProgram.setPadding(new Insets(20));
        layoutProgram.setBottom(buttons2);
        layoutProgram.setCenter(this.thread_text);
        layoutProgram.setTop(this.thread_selection);

        this.stack = new ListView<>();
        stack.setMinWidth(235);

        this.out = new ListView<>();
        this.out.setMinWidth(235);

        // create table columns for the symbol table + the table
        TableColumn<SymTableEntry, Integer> symTableKey = new TableColumn<>("Key");
        symTableKey.setCellValueFactory(new PropertyValueFactory<>("Key"));
        symTableKey.setMinWidth(127);

        TableColumn<SymTableEntry, Integer> symTableValue = new TableColumn<>("Value"); // file
        symTableValue.setCellValueFactory(new PropertyValueFactory<>("Value"));
        symTableValue.setMinWidth(127);

        this.symTable = new TableView<>();
        this.symTable.getColumns().add(symTableKey);
        this.symTable.getColumns().add(symTableValue);

        // create table columns + table for the file table
        TableColumn<FileTableEntry, Integer> tableViewKey = new TableColumn<>("Descriptor");
        tableViewKey.setCellValueFactory(new PropertyValueFactory<>("Key"));
        tableViewKey.setMinWidth(127);

        TableColumn<FileTableEntry, Integer> tableViewValue = new TableColumn<>("File");
        tableViewValue.setCellValueFactory(new PropertyValueFactory<>("Value"));
        tableViewValue.setMinWidth(127);

        this.fileTable = new TableView<>();
        this.fileTable.getColumns().add(tableViewKey);
        this.fileTable.getColumns().add(tableViewValue);

        // create table columns + table for heap
        TableColumn<HeapEntry, Integer> heapKey = new TableColumn<>("Key");
        heapKey.setCellValueFactory(new PropertyValueFactory<>("Key"));
        heapKey.setMinWidth(127);

        TableColumn<HeapEntry, Integer> heapValue = new TableColumn<>("Value");
        heapValue.setCellValueFactory(new PropertyValueFactory<>("Value"));
        heapValue.setMinWidth(127);

        this.heap = new TableView<>();
        this.heap.getColumns().add(heapKey);
        this.heap.getColumns().add(heapValue);

        // order all the detail slots, from the bottom row up
        HBox l1 = new HBox();
        l1.getChildren().addAll(this.fileTable, this.heap);
        l1.setSpacing(5);

        HBox l2 = new HBox();
        l2.getChildren().addAll(l1, this.symTable, this.out);
        l2.setSpacing(5);

        VBox l4 = new VBox();
        l4.getChildren().addAll(l1,l2);
        l4.setSpacing(5);

        HBox l3 = new HBox();
        l3.getChildren().addAll(this.stack, l4);
        l3.setSpacing(5);
        l3.setPadding(new Insets(5, 0, 5,0));
        layoutProgram.setCenter(l3);

        this.ProgramsScene = new Scene(layoutProgram, 800, 600);
        this.MenuScene = new Scene(menuLayout, 1000, 600);

        this.primaryStage.setTitle("Toy Language");
        this.primaryStage.setScene(MenuScene);
        this.primaryStage.show();
    }

    private void createMenu() {
        this.statements = new MyList<>();
        this.controllers = new MyList<>();
        this.initPrograms();
        this.selectProgram = new ListView<>();

        for (IStatement idx : this.statements.getList()) {
            this.selectProgram.getItems().add(idx.toString());
        }

        this.selectProgram.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        this.menuLabel = new Label("Select one of the following programs: ");
        this.menuLabel.setStyle("-fx-font: 25 helvetica;");
        this.menuLayout = new VBox(10);
        this.menuLayout.setAlignment(Pos.CENTER);
        this.menuLayout.getChildren().addAll(this.menuLabel, this.selectProgram, this.choose_button);
        this.menuLayout.setStyle(
                "-fx-background-radius: 5;" +
                        "-fx-padding: 7 10 7 10;" +
                        "-fx-spacing: 4;"
        );
    }

    private void buttons() {
        this.choose_button = new Button("SELECT");
        this.step_button = new Button("STEP INTO");
        this.cancel_button = new Button("CANCEL");

        this.cancel_button.setAlignment(Pos.BOTTOM_LEFT);
        this.step_button.setAlignment(Pos.BOTTOM_RIGHT);

        this.choose_button.setOnAction(e -> this.buttonClicked());
        this.step_button.setOnAction(e -> {
            try {
                this.oneStepInto();
            }catch (IOException | InterruptedException e1) {
                System.out.println(e1.toString());
            }
        });
        this.cancel_button.setOnAction(e -> this.primaryStage.setScene(this.MenuScene));
    }

    private void buttonClicked() {
        ObservableList<String> selected = this.selectProgram.getSelectionModel().getSelectedItems();
        int idx = 0;

        while(!(this.statements.getList().get(idx).toString().equals(selected.get(0)))) {
            idx++;
        }

        this.current_controller = this.controllers.getList().get(idx);

        this.primaryStage.setScene(this.ProgramsScene);
        this.createProgramWindow();
    }

    private void createProgramWindow() {
        this.step_button.setDisable(false);
        this.symTable.getItems().clear();
        this.current_controller.PowerOnExecutorService(Executors.newFixedThreadPool(2));
        this.programs = this.current_controller.removeCompletedPrg(this.current_controller.getRepository().getPrgList());
        this.main_thread = this.current_controller.getRepository().getPrgList().get(0);

        if(this.programs.size() > 0) { // if there is more than one program state => there are multiple threads
            this.thread_choice_box.getItems().add("Thread " + this.programs.get(0).getId());
        }
        this.thread_choice_box.setValue("Thread " + this.programs.get(0).getId());
        this.thread_text.setText(this.programs.get(0).getId() + "");
        this.programs.get(0).getExeStack().update(this.stack);
        this.programs.get(0).getOut().update(this.out);
        this.programs.get(0).getHeap().update(this.heap);
    }

    private void createThreadsChoiceBox() {
        this.thread_choice_box = new ComboBox<>();
        this.thread_choice_box.setPadding(new Insets(1,1,1,1));

        this.thread_selection = new HBox();
        this.thread_selection.getChildren().add(thread_choice_box);
    }

    private void threadListUpdateListener() { // aka what happens when we select another thread
        this.thread_choice_box.getSelectionModel().selectedIndexProperty().addListener(
                (selection, old_value, new_value) -> {
                    this.thread_text.setText(this.programs.get(new_value.intValue()).getId() + "");
                    this.programs.get(new_value.intValue()).getExeStack().update(this.stack); // we replace what's being displayed currently in the stack list view with the new stack, hence why we give ListView<String> stack as parameter
                    this.programs.get(new_value.intValue()).symTableUpdate(symTable);
                }
        );
    }

    private void clearThreadsChoiceBox() {
        this.thread_selection.getChildren().remove(this.thread_choice_box);
        this.thread_choice_box = new ComboBox<>();
        this.threadListUpdateListener();
        this.thread_selection.getChildren().add(thread_choice_box);
    }

    private void oneStepInto() throws InterruptedException, IOException {
        if(this.programs.size() > 0) {
            this.current_controller.garbageCollector(this.programs);
            this.current_controller.oneStepForAllPrg(this.programs);
            this.last_step = this.programs.get(0);
            this.programs = this.current_controller.removeCompletedPrg(programs);

            if(this.programs.size() > 0) {
                this.clearThreadsChoiceBox();
                this.thread_choice_box.getItems().add("Thread " + this.programs.get(0).getId());
                for(int i = 1; i < this.programs.size(); i++) {
                    this.thread_choice_box.getItems().add("Thread " + this.programs.get(i).getId());
                }
                this.thread_text.setText(this.programs.get(0).getId() + "");
                this.programs.get(0).getExeStack().update(this.stack);
                this.programs.get(0).symTableUpdate(this.symTable);
                this.programs.get(0).getOut().update(this.out);
                this.programs.get(0).updateFIleTable(this.fileTable);
                this.programs.get(0).getHeap().update(this.heap);

                this.thread_choice_box.setValue("Thread: " + this.programs.get(0).getId());
            }
            else { // last step
                this.thread_selection.getChildren().remove(this.thread_choice_box);
                this.thread_choice_box = new ComboBox<>();
                this.thread_choice_box.getItems().add("No threads");
                this.thread_text.setText(this.last_step.getId() + "");
                this.thread_choice_box.setValue("No threads");
                // ! - no exeStack bc it's empty (duh)
                this.last_step.getOut().update(this.out);
                this.last_step.getHeap().update(this.heap);
                this.last_step.updateFIleTable(this.fileTable);
                this.last_step.symTableUpdate(this.symTable);
                this.step_button.setDisable(true);
            }
        }
    }

    private void initPrograms() {

        // program_1 : a = 2+3*5; b = a+1; Print(b)

        ConstantExpression const1 = new ConstantExpression(2);
        ConstantExpression const2 = new ConstantExpression(3);
        ConstantExpression const3 = new ConstantExpression(5);
        ConstantExpression const4 = new ConstantExpression(1);
        ArithmetricExpression arith1 = new ArithmetricExpression(const2, const3, "*");
        ArithmetricExpression arith2 = new ArithmetricExpression(const1, arith1, "+");
        AssignStatement stmt2 = new AssignStatement("a", arith2);
        VarExpression var2 = new VarExpression("a");
        ArithmetricExpression arith3 = new ArithmetricExpression(var2, const4, "+");
        AssignStatement stmt3 = new AssignStatement("b", arith3);
        VarExpression var3 = new VarExpression("b");
        PrintStatement print2 = new PrintStatement(var3);
        CompStatement comp2 = new CompStatement(stmt3, print2);
        CompStatement comp3 = new CompStatement(stmt2, comp2);

        MyStack<IStatement> stack2 = new MyStack<>();
        MyDictionary<String, Integer> symTable2 = new MyDictionary<>();
        MyList<Integer> out2 = new MyList<>();
        MyDictionary<Integer, MyTuple<String, BufferedReader>> file2 = new MyDictionary<>();
        Heap heap2 = new Heap();

        ProgramState prg2 = new ProgramState(stack2, symTable2, out2, comp3, file2, heap2,1);

        Repository repo2 = new Repository("logfile2.txt");
        Controller ctrl2 = new Controller(repo2, "on");
        ctrl2.addProgram(prg2);

        // program_2 : a = 2 - 2; (IF a THEN v = 2 ELSE v = 3); Print(v)

        ArithmetricExpression arith4 = new ArithmetricExpression(const1, const1, "-");
        AssignStatement assign3 = new AssignStatement("a", arith4);
        AssignStatement assign4 = new AssignStatement("v", const2);
        AssignStatement assign1 = new AssignStatement("v", const1);
        IfStatement ifstmt1 = new IfStatement(var2,assign1,assign4);
        VarExpression var1 = new VarExpression("v");
        PrintStatement print1 = new PrintStatement(var1);
        CompStatement comp4 = new CompStatement(ifstmt1, print1);
        CompStatement comp5 = new CompStatement(assign3, comp4);

        MyStack<IStatement> stack3 = new MyStack<>();
        MyDictionary<String, Integer> symTable3 = new MyDictionary<>();
        MyList<Integer> out3 = new MyList<>();
        MyDictionary<Integer, MyTuple<String, BufferedReader>> file3 = new MyDictionary<>();
        Heap heap3 = new Heap();

        ProgramState prg3 = new ProgramState(stack3, symTable3, out3, comp5, file3, heap3,1);

        Repository repo3 = new Repository("logfile3.txt");
        Controller ctrl3 = new Controller(repo3, "on");
        ctrl3.addProgram(prg3);

        // program_3: open(var_f, "test_in"); open(var_b, "logfile1.txt"); read(var_f,var_c); Print(var_c); (if var_c then read(var_f,var_c); Print(var_c) else Print(0)); close(var_f)

        VarExpression var4 = new VarExpression("var_f");
        ReadFile read1 = new ReadFile(var4, "var_c");
        VarExpression var5 = new VarExpression("var_c");
        PrintStatement print3 = new PrintStatement(var5);
        CompStatement comp6 = new CompStatement(read1, print3);
        ConstantExpression const5 = new ConstantExpression(0);
        PrintStatement print4 = new PrintStatement(const5);
        IfStatement if1 = new IfStatement(var5, comp6, print4);
        CloseRFile close1 = new CloseRFile(var4);
        CompStatement comp7 = new CompStatement(if1, close1);
        CompStatement comp8 = new CompStatement(print3, comp7);
        ReadFile read2 = new ReadFile(var4, "var_c");
        CompStatement comp9 = new CompStatement(read2, comp8);
        OpenRFile open1 = new OpenRFile("var_f", "test.in");
        OpenRFile open_d = new OpenRFile("var_b", "logfile1.txt");
        CompStatement comp10 = new CompStatement(open_d, comp9);
        CompStatement comp21 = new CompStatement(open1, comp10);

        MyStack<IStatement> stack4 = new MyStack<>();
        MyDictionary<String, Integer> symTable4 = new MyDictionary<>();
        MyList<Integer> out4 = new MyList<>();
        MyDictionary<Integer, MyTuple<String, BufferedReader>> file4 = new MyDictionary<>();
        Heap heap4 = new Heap();

        ProgramState prg4 = new ProgramState(stack4, symTable4, out4, comp21, file4, heap4,1);

        Repository repo4 = new Repository("logfile4.txt");
        Controller ctrl4 = new Controller(repo4, "on");
        ctrl4.addProgram(prg4);

        // program_4: v = 10; new(v,20); print(100+rh(v)); print(100+rh(a));

        AssignStatement assignStatement1 = new AssignStatement("v", new ConstantExpression(10));
        ConstantExpression constantExpression = new ConstantExpression(20);
        NewStatement newStatement2 = new NewStatement("v", constantExpression);
        NewStatement newStatement3 = new NewStatement("a",new ConstantExpression(22));
        HeapReadingExpression heapReadingExpression = new HeapReadingExpression("v");
        ArithmetricExpression arithmetricExpression = new ArithmetricExpression(new ConstantExpression(100), heapReadingExpression, "+");
        PrintStatement printStatement1 = new PrintStatement(arithmetricExpression);
        HeapReadingExpression heapReadingExpression1 = new HeapReadingExpression("a");
        ArithmetricExpression arithmetricExpression1 = new ArithmetricExpression(new ConstantExpression(100), heapReadingExpression1, "+");
        PrintStatement printStatement2 = new PrintStatement(arithmetricExpression1);
        CompStatement compStatement3 = new CompStatement(printStatement1, printStatement2);
        CompStatement compStatement4 = new CompStatement(newStatement3, compStatement3);
        CompStatement compStatement5 = new CompStatement(newStatement2, compStatement4);
        CompStatement compStatement6 = new CompStatement(assignStatement1, compStatement5);

        MyStack<IStatement> stack7 = new MyStack<>();
        MyDictionary<String, Integer> symTable7 = new MyDictionary<>();
        MyList<Integer> out7 = new MyList<>();
        MyDictionary<Integer, MyTuple<String, BufferedReader>> file7 = new MyDictionary<>();
        Heap heap7 = new Heap();

        ProgramState prg7 = new ProgramState(stack7, symTable7, out7, compStatement6, file7, heap7,1);

        Repository repo7 = new Repository("logfile7.txt");
        Controller ctrl7 = new Controller(repo7, "on");
        ctrl7.addProgram(prg7);

        // program_5: v = 10; new(v,20); new(a,22); wH(a,30); print(a); print(rh(a)); a = 0;

        VarExpression varExpression1 = new VarExpression("a");
        AssignStatement assignStatement2 = new AssignStatement("v", new ConstantExpression(10));
        NewStatement newStatement4 = new NewStatement("v", new ConstantExpression(20));
        NewStatement newStatement5 = new NewStatement("a",new ConstantExpression(22));
        HeapWritingStatement heapWritingStatement = new HeapWritingStatement("a", new ConstantExpression(30));
        PrintStatement printStatement3 = new PrintStatement(varExpression1);
        HeapReadingExpression heapReadingExpression2 = new HeapReadingExpression("a");
        PrintStatement printStatement4 = new PrintStatement(heapReadingExpression2);
        AssignStatement assignStatement3 = new AssignStatement("a", new ConstantExpression(0));
        CompStatement compStatement7 = new CompStatement(printStatement4, assignStatement3);
        CompStatement compStatement8 = new CompStatement(printStatement3, compStatement7);
        CompStatement compStatement9 = new CompStatement(heapWritingStatement, compStatement8);
        CompStatement compStatement10 = new CompStatement(newStatement5, compStatement9);
        CompStatement compStatement11 = new CompStatement(newStatement4, compStatement10);
        CompStatement compStatement12 = new CompStatement(assignStatement2, compStatement11);

        MyStack<IStatement> stack8 = new MyStack<>();
        MyDictionary<String, Integer> symTable8 = new MyDictionary<>();
        MyList<Integer> out8 = new MyList<>();
        MyDictionary<Integer, MyTuple<String, BufferedReader>> file8 = new MyDictionary<>();
        Heap heap8 = new Heap();

        ProgramState prg8 = new ProgramState(stack8, symTable8, out8, compStatement12, file8, heap8,1);

        Repository repo8 = new Repository("logfile8.txt");
        Controller ctrl8 = new Controller(repo8, "on");
        ctrl8.addProgram(prg8);

        // program_6 : a = 3; b = 5; while(a<=b) { Print(a); a = a + 1; }

        AssignStatement assignStatement6 = new AssignStatement("a", new ConstantExpression(3));
        AssignStatement assignStatement7 = new AssignStatement("b", new ConstantExpression(5));
        ArithmetricExpression arithmetricExpression4 = new ArithmetricExpression(new VarExpression("a"), new ConstantExpression(1), "+");
        AssignStatement assignStatement8 = new AssignStatement("a", arithmetricExpression4);
        PrintStatement printStatement8 = new PrintStatement(new VarExpression("a"));
        CompStatement compStatement19 = new CompStatement(printStatement8, assignStatement8);
        BooleanExpression booleanExpression5 = new BooleanExpression(new VarExpression("a"), "<=", new VarExpression("b"));
        WhileStatement whileStatement = new WhileStatement(booleanExpression5, compStatement19);
        CompStatement compStatement20 = new CompStatement(assignStatement7, whileStatement);
        CompStatement compStatement21 = new CompStatement(assignStatement6, compStatement20);

        MyStack<IStatement> stack11 = new MyStack<>();
        MyDictionary<String, Integer> symTable11 = new MyDictionary<>();
        MyList<Integer> out11 = new MyList<>();
        MyDictionary<Integer, MyTuple<String, BufferedReader>> file11 = new MyDictionary<>();
        Heap heap11 = new Heap();

        ProgramState prg11 = new ProgramState(stack11, symTable11, out11, compStatement21,file11, heap11, 1);

        Repository repo11 = new Repository("logfile11.txt");
        Controller ctrl11 = new Controller(repo11, "on");
        ctrl11.addProgram(prg11);

        IStatement stmt = new CompStatement(
                new AssignStatement("v", new ConstantExpression(10)),
                new CompStatement(
                        new NewStatement("a", new ConstantExpression(22)),
                        new CompStatement(
                                new ForkStatement(
                                        new CompStatement(
                                                new HeapWritingStatement("a", new ConstantExpression(30)),
                                                new CompStatement(
                                                        new AssignStatement("v", new ConstantExpression(32)),
                                                        new CompStatement(
                                                                new PrintStatement(new VarExpression("v")),
                                                                new PrintStatement(new HeapReadingExpression("a"))
                                                        )
                                                )
                                        )
                                ),
                                new CompStatement(
                                        new PrintStatement(new VarExpression("v")),
                                        new PrintStatement(new HeapReadingExpression("a"))
                                )
                        )
                )
        );

        MyStack<IStatement> exeStack = new MyStack<>();
        MyDictionary<String, Integer> symTable = new MyDictionary<>();
        MyList<Integer> outTable = new MyList<>();
        MyDictionary<Integer, MyTuple<String, BufferedReader>> fileTable = new MyDictionary<>();
        Heap heap = new Heap();

        ProgramState prg = new ProgramState(exeStack, symTable, outTable, stmt, fileTable, heap, 1);
        Repository repo = new Repository("logfile12.txt");
        Controller ctrl = new Controller(repo, "on");
        ctrl.addProgram(prg);

        this.statements.addFirst(stmt);
        this.controllers.addFirst(ctrl);

        this.statements.addFirst(compStatement21);
        this.controllers.addFirst(ctrl11);

        this.statements.addFirst(compStatement6);
        this.controllers.addFirst(ctrl7);

        this.statements.addFirst(compStatement12);
        this.controllers.addFirst(ctrl8);

        this.statements.addFirst(comp21);
        this.controllers.addFirst(ctrl4);

        this.statements.addFirst(comp5);
        this.controllers.addFirst(ctrl3);

        this.statements.addFirst(comp3);
        this.controllers.addFirst(ctrl2);

    }
}
