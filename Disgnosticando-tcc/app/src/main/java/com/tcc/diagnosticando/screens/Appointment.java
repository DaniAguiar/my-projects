package com.tcc.diagnosticando.screens;

import androidx.appcompat.app.AppCompatActivity;
import com.tcc.diagnosticando.R;
import android.os.Bundle;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Appointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        try {

            DataSource dataSource = new DataSource(getAssets().open("diagnosis.arff"));
            Instances instances = dataSource.getDataSet();
            //System.out.println(instances.toString());

            instances.setClassIndex(33);

            NaiveBayes naiveBayes = new NaiveBayes();

            naiveBayes.buildClassifier(instances);

            Instance novo = new DenseInstance(34);
            novo.setDataset(instances);

            novo.setValue(0, "no");
            novo.setValue(1, "no");
            novo.setValue(2, "no");
            novo.setValue(3, "no");
            novo.setValue(4, "no");
            novo.setValue(5, "no");
            novo.setValue(6, "no");
            novo.setValue(7, "no");
            novo.setValue(8, "no");
            novo.setValue(9, "no");
            novo.setValue(10, "no");
            novo.setValue(11, "no");
            novo.setValue(12, "no");
            novo.setValue(13, "no");
            novo.setValue(14, "no");
            novo.setValue(15, "no");
            novo.setValue(16, "no");
            novo.setValue(17, "no");
            novo.setValue(18, "no");
            novo.setValue(19, "no");
            novo.setValue(20, "no");
            novo.setValue(21, "no");
            novo.setValue(22, "no");
            novo.setValue(23, "no");
            novo.setValue(24, "no");
            novo.setValue(25, "no");
            novo.setValue(26, "no");
            novo.setValue(27, "no");
            novo.setValue(28, "no");
            novo.setValue(29, "no");
            novo.setValue(30, "no");
            novo.setValue(31, "no");
            novo.setValue(32, "no");

            double prob[] = naiveBayes.distributionForInstance(novo);

            System.out.println("flu: " + prob[0]);
            System.out.println("gastroenteritis: " + prob[1]);
            System.out.println("tonsillitis: " + prob[2]);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}