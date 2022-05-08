 <img align = "center" src="https://github.com/acdirican/acdata-data-explorer-for-airbnb-datasets/blob/master/logo.png?raw=true" />
 
 # Robin&trade; - Data Explorer for Airbnb Datasets 

The Robin Data Explorer allows you to open, search and filter Airbnb datasets in CSV format (using comma ',' as delimeter). 

You can observe some statistics and charts about the active data. Furthermore, the software gives you the ability to save the active data in <b>CSV</b> or <b>JSON</b> formats.

As well as file from file system, you can open data sets directly from an internet source by using its URL. For instance, you can try the link below.

http://data.insideairbnb.com/the-netherlands/north-holland/amsterdam/2021-12-05/visualisations/listings.csv

<b>Desing and Implementation</b> 

The projects was fully coded in Java using basically Swing and Java collections. In this version, I have not used Java Stream and Functional Interfaces on purpose. It is beacuse of that I am going to develope another version of the same software that will be developed using Java Stream and JavaFX later.

I desinged the software by paying considerable attention to modern Software Engineering an Object Oriented Design principles. In the code, you will find a well-defined  structure in which software modules have been properly seperated and structured, including Gui, Parser and Dataset .

Some of the principles I have tried to follow are <b>Abstraction, Encapsulation, Information Hiding, Low Coupling and High Cohesion</b> etc. To this end, in addition to numerous design choices and fine-tuning in the code, I have applied vairous design patterns to obtaind a sound, flexiple and maintainable software.

I used <b>Facades</b> in modules <b>gui.charts, gui.dataviewers and dataset</b>. Some also comply with the <b>Factory</b> design pattern. Moreover, for the implementation of filtering mechanisms in the dataset package, I implemented <b>Decorator</b> design pattern. 

Unit tests and further fixes to reduce the sizes of some classes are on the way.

Please inspect the class diagram below and the screen print outs of the software to understand well its functions. 

<b>Class Diagrams</b>

<li>Main</li>

<img width="200" src="https://github.com/acdirican/robin-data-explorer-for-airbnb-datasets/blob/master/diagrams/com.acdirican.robin.png?raw=true" />

<li>Dataset Package</li>

<img width="400" src="https://github.com/acdirican/robin-data-explorer-for-airbnb-datasets/blob/master/diagrams/com.acdirican.robin.dataset.png?raw=true" />

<li>Gui Package</li>

<img width="400" src="https://github.com/acdirican/robin-data-explorer-for-airbnb-datasets/blob/master/diagrams/com.acdirican.robin.gui.png?raw=true" />

<b>Screen Print outs</b>
<li>Main Frame</li>
<img width="600" src="https://github.com/acdirican/robin-data-explorer-for-airbnb-datasets/blob/master/mainframe.jpg?raw=true" />

<li>Open Web Source (int the File menu)</li>
<img width="600" src="https://github.com/acdirican/robin-data-explorer-for-airbnb-datasets/blob/master/webresources.jpg?raw=true" />

<li>Find (int the Edit menu)</li>
<img width="400" src="https://github.com/acdirican/robin-data-explorer-for-airbnb-datasets/blob/master/find.jpg?raw=true" />

<li>Property Details (double click a table line)</li>
<img width="600" src="https://github.com/acdirican/robin-data-explorer-for-airbnb-datasets/blob/master/propertydetails.jpg?raw=true" />

<li>Statistics Dialog</li>
<img width="600" src="https://github.com/acdirican/robin-data-explorer-for-airbnb-datasets/blob/master/statistics.jpg?raw=true" />
