 <img align = "center" src="https://github.com/acdirican/acdata-data-explorer-for-airbnb-datasets/blob/master/logo.png?raw=true" width="150" />
 
 # ACData - Data Explorer for Airbnb Datasets 

The ACData Data Explorer allows you to open, search and filter Airbnb datasets in CSV format (, as delimeter). You can also observe some statistics and charts about the active data and save it.

You can open data sets directly from an internet source by using its URL.

<b>Desing and Implementation</b> 

The projects was fully coded in Java using Swing and Java collections. In this version, I did not use Java Stream and Functinal Interfaces on purpose. It is beacuse of that I am going to develope another version of the same software that will be developed using Java Stream and even JavaFX later.

I desinged the software by paying considerable attention to modern Software Engineering an Object Oriented Design principles. In the code, you will find a well-defined  structure in which software modules have been properly seperated and structured, including Gui, Parser and Dataset .

Some of the principles I have tried to follow are <b>Abstraction, Encapsulation, Information Hiding, Low Coupling and High Cohesion, no Monster Class</b>. To this end, in addition to numerous fine-tuning in the code, I applied vairous design patterns to obtaind a sound, flexiple and maintainable code.

I used <b>Facades</b> in modukes <b>gui.charts, gui.dataviewers and dataset</b>. These also comply with the <b>Factory</b> design pattern. Moreover, for the implementatipn of filtering mechanisms in the dataset package, I applied <b>Decorator</b> design pattern. 

Please inspect the class diagram below and the screen print outs of the software to understand well its functions. 

<b>Class Diagram</b>

<b>Screen Print outs</b>
<li>Main Frame</li>
<img width="700" src="https://github.com/acdirican/acdata-data-explorer-for-airbnb-datasets/blob/master/mainframe.jpg?raw=true" />

<li>Statistics Dialog</li>
<img width="700" src="https://github.com/acdirican/acdata-data-explorer-for-airbnb-datasets/blob/master/statistics.jpg?raw=true" />
