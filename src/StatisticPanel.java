import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;
import Models.ReservationWithPrice;
import Services.ReservationService;

public class StatisticPanel extends JFrame {

    private JPanel statisticPanel;
    private JButton backButton;
    private JPanel plotPanel;

    public StatisticPanel() {
        super("Przychody w firmie");

        UiDesigner.applyStyles();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(this.statisticPanel);
        this.setSize(800, 600);
        setLocationRelativeTo(null);

        try {
            setIconImage(ImageIO.read(new File("src/icon.png")));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Wystąpił błąd przy wczytywaniu icon.png.");
        }

        plotPanel.setLayout(new BorderLayout());
        plotPanel.add(createChartPanel(), BorderLayout.CENTER);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminDashboard adminDashboard = new AdminDashboard();
                adminDashboard.setVisible(true);
                dispose();
            }
        });
    }

    private JPanel createChartPanel() {
        XYSeriesCollection dataset = createDataset();
        JFreeChart chart = ChartFactory.createXYLineChart(
                "",
                "Data",
                "Cena",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        XYPlot plot = chart.getXYPlot();
        DateAxis dateAxis = new DateAxis("Data");
        dateAxis.setDateFormatOverride(new SimpleDateFormat("dd-MM-yyyy"));
        dateAxis.setTimeZone(TimeZone.getTimeZone("Europe/Warsaw"));
        plot.setDomainAxis(dateAxis);
        NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
        numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        plot.setRangeAxis(numberAxis);

        // Użycie XYSplineRenderer do wygładzenia linii
        XYSplineRenderer renderer = new XYSplineRenderer();
        renderer.setPrecision(10);  // Możesz dostosować ten parametr, aby uzyskać odpowiedni poziom wygładzenia
        plot.setRenderer(renderer);
        renderer.setSeriesShapesVisible(0, false);
        return new ChartPanel(chart);
    }

    private XYSeriesCollection createDataset() {
        XYSeries series = new XYSeries("Przychody");

        try {
            List<ReservationWithPrice> reservations = ReservationService.getReservationsWithPrice();

            for (ReservationWithPrice reservation : reservations) {
                series.add(reservation.getDate().getTime(), reservation.getPrice());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        return dataset;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StatisticPanel().setVisible(true);
            }
        });
    }
}
