package com.tcc.diagnosticando.screens;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.tcc.diagnosticando.R;
import com.tcc.diagnosticando.screens.principal.Profiles;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Objects;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class DiagnosisResult extends AppCompatActivity {

    ArrayList<String> diagnosis = new ArrayList<>();
    ListView listViewDiagnosis;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Intent intent = getIntent();
        ArrayList<String> answers = intent.getStringArrayListExtra("answers");

        listViewDiagnosis = findViewById(R.id.listViewDiagnosis);

        diagnosisCalc(answers);
    }

    private void diagnosisCalc(ArrayList<String> answers) {
        try {

            DataSource dataSource = new DataSource(getAssets().open("diagnosis.arff"));
            Instances instances = dataSource.getDataSet();

            instances.setClassIndex(answers.size());

            NaiveBayes naiveBayes = new NaiveBayes();

            naiveBayes.buildClassifier(instances);

            int classResultDiagnosis = 1;
            int answersAttributes = answers.size();
            int totalAttributes = answersAttributes + classResultDiagnosis;

            Instance newInstance = new DenseInstance(totalAttributes);
            newInstance.setDataset(instances);

            for (int i = 0; i < answersAttributes; i++) {
                newInstance.setValue(i, answers.remove(0));
            }

            double[] probability = naiveBayes.distributionForInstance(newInstance);

            showResult(probability);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showResult(double[] probability) {
        double comparator = 0.00;
        if (probability[0] >= comparator){
            diagnosis.add("Chances de ser Gripe: " + BigDecimal.valueOf(probability[0]*100).setScale(2, RoundingMode.FLOOR) + "%");
        }
        if (probability[1] >= comparator){
            diagnosis.add("Chances de ser Gastroenterite " + BigDecimal.valueOf(probability[1]*100).setScale(2, RoundingMode.FLOOR) + "%");
        }
        if (probability[2] >= comparator){
            diagnosis.add("Chances de ser Amidalite " + BigDecimal.valueOf(probability[2]*100).setScale(2, RoundingMode.FLOOR) + "%");
        }
        if (probability[0] < probability[3] && probability[1] < probability[3] && probability[2] < probability[3]){
            diagnosis.add("Não foi possível obter um diagnóstico preciso deste caso");
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, diagnosis);

        listViewDiagnosis.setAdapter(arrayAdapter);
    }
}