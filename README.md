![Alt text](https://github.com/acdirican/acddata-data-explorer-for-airbnb-datasets/blob/master/logo.png?raw=true)

# ACData - Data Explorer for Airbnb Datasets

The ACData Data Explorer allows you to open, search and filter Airbnb datasets in CSV format (, as delimeter). You can also observe some statistics and charts about the active data and save it.

You can open data sets directly from an internet source by using its URL.

<b>Desing and Implementation</b> 

The projects was fully coded in Java using Swing and Java collections. In this version, I did not use Java Stream and Functinal Interfaces on purpose. It is beacuse of that I am going to develope another version of the same software that will be developed using Java Stream and even JavaFX later.

I desinged the software by paying considerable attention to modern Software Engineering an Object Oriented Design principles. In the code, you will find a well-defined  structure in which software modules have been properly seperated, including Gui (View), Parser (Model) and Dataset (Controller).

Some of the principles I have tried to follow are <b>Abstraction, Encapsulation, Information Hiding and Low Coupling and High Cohesion</b>. To this end, in addition to numerous minor fine-tuning in the code, I applied vairous design patterns to obtaind a sound, flexiple and maintai code.

I used <b>Facades</b> in packages <b>gui.charts, gui.dataviewers and dataset</b>. These also comply with the <b>Factory</b> design pattern. Moreover, for the implementatipn of filtering mechanisms in the dataset package, I applied <b>Decorator</b> design pattern. 

Please inspect the class diagram below and the screen print outs of the software to understand well its functions. 

<b>Class Diagram</b>

<b>Screen Print outs</b>
