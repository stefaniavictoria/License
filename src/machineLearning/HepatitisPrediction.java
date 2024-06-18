package machineLearning;

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.gui.beans.DataSource;

import java.io.File;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.Logistic;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;

public class HepatitisPrediction {

    public static void main(String[] args) throws Exception {
    	Instances data = null;
    	try {
            ArffLoader loader = new ArffLoader();
            loader.setFile(new File("C:\\Users\\user\\eclipse-workspace\\License\\PatientDataset.arff"));
            data = loader.getDataSet();

            //System.out.println("Loaded dataset: " + data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    	 // data.setClassIndex(data.numAttributes() - 1); // Assuming the last attribute is the class label
    	 Instances modifiedData = null;
    	if(data != null)
    	{
    		 modifiedData = new Instances(data);
    	}
    	
    	FastVector bilirubinValues = new FastVector(4);
        bilirubinValues.addElement("Low");
        bilirubinValues.addElement("Normal");
        bilirubinValues.addElement("High");
        bilirubinValues.addElement("Very High");
        Attribute bilirubinAttr = new Attribute("BILIRUBIN", bilirubinValues);
        modifiedData.deleteAttributeAt(modifiedData.attribute("BILIRUBIN").index());
        modifiedData.insertAttributeAt(bilirubinAttr, modifiedData.numAttributes());

        FastVector proteinValues = new FastVector(3);
        proteinValues.addElement("Low");
        proteinValues.addElement("Normal");
        proteinValues.addElement("High");
        
        Attribute albuminAttr = new Attribute("ALBUMIN", proteinValues);
        modifiedData.deleteAttributeAt(modifiedData.attribute("ALBUMIN").index());
        modifiedData.insertAttributeAt(albuminAttr, modifiedData.numAttributes());
        
        Attribute protimeAttr = new Attribute("PROTIME", proteinValues);
        modifiedData.deleteAttributeAt(modifiedData.attribute("PROTIME").index());
        modifiedData.insertAttributeAt( protimeAttr, modifiedData.numAttributes());

    
    	if(modifiedData != null)
    	{
    		 for (int i = 0; i < modifiedData.numInstances(); i++) {
     		    double bilirubin = modifiedData.instance(i).value(modifiedData.attribute("BILIRUBIN"));
     		    double albumin = modifiedData.instance(i).value(modifiedData.attribute("ALBUMIN"));
     		    double protime = modifiedData.instance(i).value(modifiedData.attribute("PROTIME"));

     		    if (bilirubin < 0.2) {
     		        modifiedData.instance(i).setValue(modifiedData.attribute("BILIRUBIN"), "Low");
     		    } else if (bilirubin <= 1.2) {
     		        modifiedData.instance(i).setValue(modifiedData.attribute("BILIRUBIN"), "Normal");
     		    } else if (bilirubin <= 3.0) {
     		        modifiedData.instance(i).setValue(modifiedData.attribute("BILIRUBIN"), "High");
     		    } else {
     		        modifiedData.instance(i).setValue(modifiedData.attribute("BILIRUBIN"), "Very High");
     		    }

     		    if (albumin < 3.4) {
     		        modifiedData.instance(i).setValue(modifiedData.attribute("ALBUMIN"), "Low");
     		    } else if (albumin <= 5.4) {
     		        modifiedData.instance(i).setValue(modifiedData.attribute("ALBUMIN"), "Normal");
     		    } else {
     		        modifiedData.instance(i).setValue(modifiedData.attribute("ALBUMIN"), "High");
     		    }

     		    if (protime < 11) {
     		        modifiedData.instance(i).setValue(modifiedData.attribute("PROTIME"), "Low");
     		    } else if (protime <= 13.5) {
     		        modifiedData.instance(i).setValue(modifiedData.attribute("PROTIME"), "Normal");
     		    } else {
     		        modifiedData.instance(i).setValue(modifiedData.attribute("PROTIME"), "High");
     		    }
     		}
     	 
     	 System.out.println("Modified dataset: " + modifiedData);
    	}
    	

        // Split data into training and testing sets (80-20 split)
        int trainSize = (int) Math.round( modifiedData.numInstances() * 0.8);
        int testSize =  (modifiedData).numInstances() - trainSize;
        Instances trainData = new Instances( modifiedData, 0, trainSize);
        Instances testData = new Instances( modifiedData, trainSize, testSize);

       // Choose a classifier (e.g., RandomForest, Logistic, J48)
     Classifier classifier = new RandomForest();
       
        // Train the classifier
        classifier.buildClassifier(trainData);

        // Evaluate the classifier
        Evaluation evaluation = new Evaluation(trainData);
        evaluation.evaluateModel(classifier, testData);

        // Print evaluation results
        System.out.println(evaluation.toSummaryString());
        System.out.println(evaluation.toClassDetailsString());
        System.out.println(evaluation.toMatrixString());
    }
}
