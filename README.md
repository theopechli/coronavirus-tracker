# coronavirus-tracker
This is a Spring Boot project that gets
the latest COVID-19 data from the following csv:  
[CSSEGISandData/COVID-19](https://github.com/CSSEGISandData/COVID-19).  

The front end is created with thymeleaf and
it presents the data as a table with 6 columns.
The first 4 columns are the same as the csv file
while the last two are the total reported cases
and the new reported cases since the previous date.

# Credits
This was done by following a tutorial while
making appropriate changes, with the biggest two being
the csv parser (I used univocity-parsers, see [3rd-party-licenses.md](https://github.com/theopechli/coronavirus-tracker/blob/master/3rd-party-licenses.md))
and removing the bootstrap dependency for the front end.

**[Java Brains](https://www.youtube.com/channel/UCYt1sfh5464XaDBH0oH_o7Q)**  
**[Building a Coronavirus tracker app with Spring Boot and Java - Java Brains Tutorial](https://www.youtube.com/watch?v=8hjNG9GZGnQ)**