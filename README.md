# OSM Measure Repository

The `OSM Measure Repository` is a collection of measures for assessing OpenStreetMap (OSM) data.  Many measures focus on data quality while others do not, thereby providing context to the data quality measures.  The [OSM Measure Repository](https://osm-measure.geog.uni-heidelberg.de) can be used by everyone.  New measures can, however, only be added by scientists of the GIScience Group of the Institute of Geography at Heidelberg University.

## Scientific Publications

If you consider this repository to be useful for your work, we would be happy if you would consider publishing our relevant papers:

* F-B Mocnik: **Linked Open Data Vocabularies for Semantically Annotated Repositories of Data Quality Measures** Proceedings of the 10th International Conference on Geographic Information Science (GIScience), 2018

* F-B Mocnik, A Mobasheri, L Griesbaum, M Eckle, C Jacobs, and C Klonner: [**A grounding-based ontology of data quality measures**](http://josis.org/index.php/josis/article/viewFile/360/197) Journal of Spatial Information Science, 16, 2018

* F-B Mocnik: [**A Novel Identifier Scheme for the ISEA Aperture 3 Hexagon Discrete Global Grid System.**](http://doi.org/10.1080/15230406.2018.1455157) Cartography and Geographic Information science, 2018

* F-B Mocnik, A Zipf, and M Raifer: [**The OpenStreetMap folksonomy and its evolution.**](http://doi.org/10.1080/10095020.2017.1368193) Geo-spatial Information Science, 20(3), 2017, 219–230

## Adding a new measure

For adding a measure to the repository, follow the instructions listed below.  Please be aware that all measures included in the repository are licensed under the [MIT license](https://github.com/giscience/measures-rest/blob/master/LICENSE).

### Step 1: Fork the Github repository

First, you have to fork the [https://github.com/giscience/osm-measure-repository](https://github.com/giscience/osm-measure-repository).  To do so, open the website and click on the button **Fork** at the top right side.  You are required to be logged in to create a fork.  As a result, you should have your own repository named *https://github.com/your-github-account/osm-measure-repository*.

### Step 2: Create the import in the OSM Measure Repository

Create a new measure in the *OSM Measure Repository*.  Click on the <i class="fas fa-edit"/>-symbol and rename the measure to fit your needs.  Then, open the code view by clicking on the <i class="fas fa-code"/>-symbol.  Insert the following **SOAP directive**:

```java
// import from github/your-name/osm-measure-repository //
```

Observe that you have to adapt the “your-name” part of the directive in order to match your Github account name.  Below the code, you will find a short message stating the name that you will use for your JAVA class.  If the name of your measure is “Topological completeness”, the name of your JAVA class would be “MeasureTopologicalCompleteness”.  Memorize this name because you will need it later.

### Step 3: Clone the Github repository and prepare the measure

For cloning the Github repository to your computer, you can either use a GUI or the command line.  If you choose the latter option, you have to type:

```bash
git clone https://github.com/your-github-account/osm-measure-repository
```

Now, you have a local copy of the Github repository on your computer.  For preparing a new measure, open a terminal/bash console and navigate to the path in which you have placed the cloned data.  In this path, execute:

```bash
./do --add MeasureTopologicalCompleteness
```

Observe that you have to replace the name of the measure by the name that you have memorized in Step 2.  Voilà, a directory for the new measure appears.  The directory is named accordingly, and it contains a class for a measure.

### Step 4: Implement the measure

Now that you have prepared the measure, you can start with the actual implementation.  What you actually do is to overwrite the method `compute` in the JAVA class that you will find in the directory of you measure (`src/...`).

You will find more detailed information about how to implement a measure in the documentation of the library [Measures REST OSHDB](https://gitlab.gistools.geog.uni-heidelberg.de/giscience/dfg-intrinsic-data-quality/measures-rest-oshdb).

### Step 5: Commit and push

In order to make the implementation of the measure including most recent changes available to the *OSM Measure Repository*, you have to commit your changes:

```bash
git add *
git commit -m "MeasureTopologicalCompleteness introduced/improved"
```

Then, you can easily upload the measure to Github:

```bash
git push
```

### Step 6: Run the measure

Go back to the website of the [OSM Measure Repository](https://osm-measure-edit.geog.uni-heidelberg.de).  Just enable the measure using the <i class="fas fa-toggle-on"/>-element and (re-)start the server by clicking on the <i class="fas fa-play"/>-symbol.

Congrats, you are done!  Your measure should start and be available within the next seconds.

### Step 7 (optional): Publish the measure

Do not forget to even add semantic information about the measure on the website of the [OSM Measure Repository](https://osm-measure-edit.geog.uni-heidelberg.de).  This is required when publishing the measure.

Please test the measure in before you want to publish it.  Changes are much harder to be made after having published the measure.  If you are ready to publish, please go back to your Github repository.  There, you will find a button `New pull request`.  Use this button to indicate that you would like to publish your measure.  Also contact the administrators of the `OSM Measure Repository` in person to discuss the publishing process.

Thank you for contributing!

## Author

This software is written and maintained by the GIScience Research Group, Institute of Geography, Heidelberg University.

The development has been supported by the DFG project *A framework for measuring the fitness for purpose of OpenStreetMap data based on intrinsic quality indicators* (FA 1189/3-1).

(c) by Heidelberg University, 2018–2019.

## License

The code is licensed under the [MIT license](https://github.com/giscience/measures-rest/blob/master/LICENSE).
