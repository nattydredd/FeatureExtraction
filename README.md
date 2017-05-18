# FeatureExtraction
Kepler Light Curve Feature Extraction

The feature extraction software converts Kepler Light Curve data into a set of statistical features
for use with the Weka machine learning classifiers. The feature sets are designed to be used with the
Exo-AL project availabale at: https://github.com/NathanDuran/Exo-AL

-Light Curve data must be included in .csv format in the resource directory

-The list of stars to be processed must be included as text file lists of stars by Kepler ID

-To run the JAR from within its directory:

java -jar FeatureExtraction.jar {host star list} {non-host star list} {set name}

-Two small test sets are included for demonstration purposes "HostTEST" and "NonHostTEST"

-The output .arff file will be created in the /resources/output directory
