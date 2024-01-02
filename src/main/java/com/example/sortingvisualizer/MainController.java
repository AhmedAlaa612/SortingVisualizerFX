package com.example.sortingvisualizer;

import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

public final class MainController implements Initializable {
    @FXML
    private BorderPane displayBorderPane, display2BorderPane, display3BorderPane;
    @FXML
    private Slider arraySizeSlider, delaySlider;
    @FXML
    private Button SortBtn, randomizeBtn, setSortedBtn, setReversedBtn;
    @FXML
    private Text timeQuick, timeBubble, timeSelection, interchangesQuick, interchangesBubble, interchangesSelection, comparisonsQuick, comparisonsBubble, comparisonsSelection;
    private int interchangesQuickCount, interchangesBubbleCount, interchangesSelectionCount, comparisonsQuickCount, comparisonsBubbleCount, comparisonsSelectionCount, timeQuickCount, timeBubbleCount, timeSelectionCount;
    private int TOTAL_NUMBER_OF_BARS;
    private int DELAY_TIME;
    final private CategoryAxis xAxis = new CategoryAxis(), xAxis2 = new CategoryAxis(), xAxis3 = new CategoryAxis();
    final private NumberAxis yAxis = new NumberAxis(), yAxis2 = new NumberAxis(), yAxis3 = new NumberAxis();
    final private XYChart.Series<Object, Object> series = new XYChart.Series<>(), series2 = new XYChart.Series<>(), series3 = new XYChart.Series<>();
    final private BarChart  barChart = new BarChart(xAxis, yAxis), barChart2 = new BarChart(xAxis2, yAxis2), barChart3 = new BarChart(xAxis3, yAxis3);
    private Integer[] randomNumbers;
    private long startTime;
    private final String
            MAIN_THEME = "-fx-background-color: #00D8FA;",
            SELECTED_BARS_COLOR_FILL = "-fx-background-color: #FFAAAA;",
            SELECTED_BARS_BORDER_COLOR = "-fx-border-color: #FF7F7F;",
            CURRENT_INDEX_COLOR = "-fx-background-color: #39FF14;",
            BARS_DISABLE_COLOR = "-fx-background-color: #808080";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TOTAL_NUMBER_OF_BARS = (int) arraySizeSlider.getValue();
        arraySizeSlider.valueProperty().addListener(e -> {
            TOTAL_NUMBER_OF_BARS = (int) arraySizeSlider.getValue();
            randomize();
        });
        randomize();
        DELAY_TIME = (int) delaySlider.getValue();
        delaySlider.valueProperty().addListener(e -> {
            DELAY_TIME = (int) delaySlider.getValue();
        });

        SortBtn.setOnMouseClicked(e -> sort());
    }
    void clearText(){
        Platform.runLater(() -> {
            timeQuick.setText("Time: ");
            timeBubble.setText("Time: ");
            timeSelection.setText("Time: ");
            interchangesQuick.setText("Interchanges: ");
            interchangesBubble.setText("Interchanges: ");
            interchangesSelection.setText("Interchanges: ");
            comparisonsQuick.setText("Comparisons: ");
            comparisonsBubble.setText("Comparisons: ");
            comparisonsSelection.setText("Comparisons: ");
        });
    }
    private void updateComparisonText(Text textNode, int comparisons) {
        Platform.runLater(() -> textNode.setText("Comparisons: " + comparisons));
    }
    private void updateTimeText(Text textNode) {
        long time = System.currentTimeMillis() - startTime;
        Platform.runLater(() -> textNode.setText("Time: " + time + " ms"));
    }
    private void updateInterchangeText(Text textNode, int interchanges) {
        Platform.runLater(() -> textNode.setText("Interchanges: " + interchanges));
    }
    private void sort(){
        setAllDisable(true);
        clearText();
        startTime = System.currentTimeMillis();
        interchangesQuickCount = interchangesBubbleCount = interchangesSelectionCount =
                comparisonsQuickCount = comparisonsBubbleCount = comparisonsSelectionCount =
                timeQuickCount = timeBubbleCount = timeSelectionCount = 0;
        Platform.runLater(() -> {
            bubbleSort();
            quickSort();
            selectionSort();
        });
    }
    private void delay() {
        try {
            Thread.sleep(DELAY_TIME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private Integer[] generateRandomNumbers(int size) {
        Integer[] numbers = new Integer[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = new Random().nextInt(size) + 1;
        }
        return numbers;
    }
    private void setChart(XYChart.Series<Object, Object> s, BarChart<Object, Object> b, CategoryAxis x, NumberAxis y, BorderPane display, Integer[] numbers) {
        s.getData().clear();
        b.getData().clear();
        for (int i = 0; i < TOTAL_NUMBER_OF_BARS; i++) {
            s.getData().add(new XYChart.Data<>(Integer.toString(i), numbers[i]));
        }
        b.getData().add(s);
        x.setOpacity(0);
        y.setOpacity(0);

        display.setCenter(b);

        for (int i = 0; i < TOTAL_NUMBER_OF_BARS; i++)
            s.getData().get(i).getNode().setStyle(MAIN_THEME);

        b.setBarGap(0);
        b.setCategoryGap(3);
        b.setLegendVisible(false);
        b.setAnimated(false);
        b.setHorizontalGridLinesVisible(false);
        b.setVerticalGridLinesVisible(false);
        b.setHorizontalZeroLineVisible(false);
        b.setVerticalZeroLineVisible(false);
    }
    @FXML
    private void randomize() {
        clearText();
        randomNumbers = generateRandomNumbers(TOTAL_NUMBER_OF_BARS);
        Platform.runLater(
                () -> {
                    setChart(series, barChart, xAxis, yAxis, displayBorderPane, randomNumbers);
                    setChart(series2, barChart2, xAxis2, yAxis2, display2BorderPane, randomNumbers);
                    setChart(series3, barChart3, xAxis3, yAxis3, display3BorderPane, randomNumbers);
                }

        );
    }
    private Integer[] generateSortedNumbers(int size) {
        Integer[] numbers = new Integer[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = i + 1;
        }
        return numbers;
    }
    @FXML
    private void setSorted(){
        clearText();
        Integer[] numbers = generateSortedNumbers(TOTAL_NUMBER_OF_BARS);
        Platform.runLater(
                () -> {
                    setChart(series, barChart, xAxis, yAxis, displayBorderPane, numbers);
                    setChart(series2, barChart2, xAxis2, yAxis2, display2BorderPane, numbers);
                    setChart(series3, barChart3, xAxis3, yAxis3, display3BorderPane, numbers);
                }
        );
    }
    private Integer[] generateReversedNumbers(int size) {
        Integer[] numbers = new Integer[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = size - i;
        }
        return numbers;
    }
    @FXML
    private void setReversed(){
        clearText();
        Integer[] numbers = generateReversedNumbers(TOTAL_NUMBER_OF_BARS);
        Platform.runLater(
                () -> {
                    setChart(series, barChart, xAxis, yAxis, displayBorderPane, numbers);
                    setChart(series2, barChart2, xAxis2, yAxis2, display2BorderPane, numbers);
                    setChart(series3, barChart3, xAxis3, yAxis3, display3BorderPane, numbers);
                }

        );
    }
    private void setAllDisable(boolean b) {
        Platform.runLater(() -> {
            arraySizeSlider.setDisable(b);
            SortBtn.setDisable(b);
            randomizeBtn.setDisable(b);
            setSortedBtn.setDisable(b);
            setReversedBtn.setDisable(b);
        });
    }
    private void changeStyleEffect(int index, String style, XYChart.Series<Object, Object> s) {
        Platform.runLater(() -> {
            try {
                s.getData().get(index).getNode().setStyle(style);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void changeStyleEffect(int index, String style, String borderColor, XYChart.Series<Object, Object> s) {
        Platform.runLater(() -> {
            try {
                s.getData().get(index).getNode().setStyle(style + borderColor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void changeStyleEffect(int index1, String style1, int index2, String style2, XYChart.Series<Object, Object> s) {
        Platform.runLater(() -> {
            try {
                s.getData().get(index1).getNode().setStyle(style1);
                s.getData().get(index2).getNode().setStyle(style2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void changeStyleEffect(int index1, String style1, String borderColor1, int index2, String style2, String borderColor2, XYChart.Series<Object, Object> s) {
        Platform.runLater(() -> {
            try {
                (s.getData().get(index1)).getNode().setStyle(style1 + borderColor1);
                (s.getData().get(index2)).getNode().setStyle(style2 + borderColor2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void barsDisableEffect(XYChart.Series<Object, Object> s) {
        Platform.runLater(() -> {
            for (int i = 0; i < TOTAL_NUMBER_OF_BARS; i++) {
                s.getData().get(i).getNode().setStyle(BARS_DISABLE_COLOR);
            }
        });
    }

    private void barsDisableEffect(int i, int j, XYChart.Series<Object, Object> s) {
        Platform.runLater(() -> {
            for (int x = i; x < j; x++) {
                s.getData().get(x).getNode().setStyle(BARS_DISABLE_COLOR);
            }
        });
    }

    private void bubbleSort() {
        new Thread(() -> {
            barsDisableEffect(series);
            boolean flag;
            for (int i = 0; i < TOTAL_NUMBER_OF_BARS - 1; i++) {
                flag = false;
                for (int j = 0; j < TOTAL_NUMBER_OF_BARS - i - 1; j++) {
                    updateComparisonText(comparisonsBubble, ++comparisonsBubbleCount);
                    updateTimeText(timeBubble);
                    changeStyleEffect(j, SELECTED_BARS_COLOR_FILL, SELECTED_BARS_BORDER_COLOR, j + 1, SELECTED_BARS_COLOR_FILL, SELECTED_BARS_BORDER_COLOR, series);
                    delay();
                    if ((int) series.getData().get(j).getYValue() > (int) (series.getData().get(j + 1)).getYValue()) {
                        CountDownLatch latch = new CountDownLatch(1);
                        int finalJ = j;
                        Platform.runLater(() -> {
                            ParallelTransition pt = swapAnimation(finalJ, finalJ + 1, series);
                            pt.setOnFinished(e -> latch.countDown());
                            pt.play();
                        });
                        try {
                            latch.await();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        updateInterchangeText(interchangesBubble, ++interchangesBubbleCount);
                        flag = true;
                    }
                    changeStyleEffect(j, MAIN_THEME, j + 1, MAIN_THEME, series);
                }
                if (!flag) break;
            }
            setAllDisable(false);
        }).start();
    }

    // for swap Animation effect
    private ParallelTransition swapAnimation(int d1, int d2, XYChart.Series<Object, Object> s) {
        // get the precise location of the node in X axis
        double a1 = s.getData().get(d1).getNode().getParent().localToParent(s.getData().get(d1).getNode().getBoundsInParent()).getMinX();
        double a2 = s.getData().get(d1).getNode().getParent().localToParent(s.getData().get(d2).getNode().getBoundsInParent()).getMinX();

        // if any swap occur then we get the location of our node where it is swapped
        double translated1 = (s.getData().get(d1)).getNode().getTranslateX();
        double translated2 = (s.getData().get(d2)).getNode().getTranslateX();

        TranslateTransition t1 = new TranslateTransition(Duration.millis(DELAY_TIME), (s.getData().get(d1)).getNode());
        t1.setByX(a2 - a1);
        TranslateTransition t2 = new TranslateTransition(Duration.millis(DELAY_TIME), (s.getData().get(d2)).getNode());
        t2.setByX(a1 - a2);
        ParallelTransition pt = new ParallelTransition(t1, t2);
        // ParallelTransition will run t1 and t2 in parallel
        pt.statusProperty().addListener((e, old, curr) -> {
            if (old == Animation.Status.RUNNING) {
                (s.getData().get(d2)).getNode().setTranslateX(translated1);
                (s.getData().get(d1)).getNode().setTranslateX(translated2);

                int temp = (int) (s.getData().get(d2)).getYValue();
                ( s.getData().get(d2)).setYValue((s.getData().get(d1)).getYValue());
                (s.getData().get(d1)).setYValue(temp);
            }
        });
        return pt;
    }
    // QUICK SORT
    private void quickSort() {
        new Thread(() -> {
            setAllDisable(true);
            quickSortRec(0, TOTAL_NUMBER_OF_BARS - 1);
            setAllDisable(false);
        }).start();
    }
    private void quickSortRec(int startIdx, int endIdx) {
        int idx = partition(startIdx, endIdx);

        if (startIdx < idx - 1) {
            quickSortRec(startIdx, idx - 1);
        }

        if (endIdx > idx) {
            quickSortRec(idx, endIdx);
        }
    }
    private int partition(int left, int right) {
        barsDisableEffect(left, right, series2);
        int pivot = (int) (series2.getData().get(left)).getYValue();
        Node pivotNode = (series2.getData().get(left)).getNode();
        Platform.runLater(() -> pivotNode.setStyle(CURRENT_INDEX_COLOR));
        delay();
        while (left <= right) {
            while ((int) ((series2.getData().get(left))).getYValue() < pivot) {
                int finalLeft = left;
                changeStyleEffect(finalLeft, SELECTED_BARS_COLOR_FILL, SELECTED_BARS_BORDER_COLOR, series2);
                delay();
                left++;
                updateComparisonText(comparisonsQuick, ++comparisonsQuickCount);
                updateTimeText(timeQuick);
            }
            while ((int) ((series2.getData().get(right))).getYValue() > pivot) {
                int finalRight = right;
                changeStyleEffect(finalRight, SELECTED_BARS_COLOR_FILL, SELECTED_BARS_BORDER_COLOR, series2);
                delay();
                updateComparisonText(comparisonsQuick, ++comparisonsQuickCount);
                updateTimeText(timeQuick);
                right--;
            }
            changeStyleEffect(left, SELECTED_BARS_COLOR_FILL, SELECTED_BARS_BORDER_COLOR, right, SELECTED_BARS_COLOR_FILL, SELECTED_BARS_BORDER_COLOR, series2);
            delay();
            if (left <= right) {
                if (left != right) {
                    int finalLeft = left, finalRight = right;
                    CountDownLatch latch = new CountDownLatch(1);
                    Platform.runLater(() -> {
                        ParallelTransition pt = swapAnimation(finalLeft, finalRight, series2);
                        pt.setOnFinished(e -> latch.countDown());
                        pt.play();
                        updateInterchangeText(interchangesQuick, ++interchangesQuickCount);
                    });
                    try {
                        latch.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                changeStyleEffect(left, MAIN_THEME, right, MAIN_THEME, series2);
                left++;
                right--;
            }
            changeStyleEffect(left, MAIN_THEME, right, MAIN_THEME, series2);
        }
        return left;
    }
    private void selectionSort() {
        new Thread(() -> {
            setAllDisable(true);
            barsDisableEffect(series3);
            int i, j, k;
            i = 0;
            while (i < TOTAL_NUMBER_OF_BARS - 1) {
                k = i;
                j = i + 1;
                int finalI = i;
                while (j < TOTAL_NUMBER_OF_BARS) {
                    int finalJ = j, finalK = k;
                    changeStyleEffect(finalI, CURRENT_INDEX_COLOR, series3);
                    changeStyleEffect(finalJ, SELECTED_BARS_COLOR_FILL, SELECTED_BARS_BORDER_COLOR, finalK, SELECTED_BARS_COLOR_FILL, SELECTED_BARS_BORDER_COLOR, series3);
                    delay();
                    updateComparisonText(comparisonsSelection, ++comparisonsSelectionCount);
                    updateTimeText(timeSelection);
                    if ((int) (series3.getData().get(j)).getYValue() < (int) (series3.getData().get(k)).getYValue()) {
                        k = j;
                    }
                    changeStyleEffect(finalJ, MAIN_THEME, finalK, MAIN_THEME, series3);
                    j++;
                }
                int finalK = k;
                if (k != i) {
                    CountDownLatch latch = new CountDownLatch(1);
                    Platform.runLater(() -> {
                        ParallelTransition pt = swapAnimation(finalI, finalK, series3);
                        pt.setOnFinished(e -> latch.countDown());
                        pt.play();
                    });
                    try {
                        latch.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    updateInterchangeText(interchangesSelection, ++interchangesSelectionCount);
                }
                    changeStyleEffect(finalI, MAIN_THEME, series3);
                    i++;
            }
        }).start();
    }

}