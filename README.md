scala-playground
================

A tool for scala to be used as a command line too similar to irb, with useful scripts that helps initializing settings for different tools, such as HBase, Spring, Thrift serializers, etc


How to Use

----

Copy all the jar files in your project into lib/ folder. 

Run scala.sh

   it will bring you into the scala interactive mode with the correct class path.
   - All the class imports are stored into: /tmp/imports.scala
   open this file in your favoriate text editor to search, copy, paste the class you are looking for to the console.

   - All the xml files packed into jars are stored into: /tmp/xmls.txt
   open this file in your favoriate text editor, and use it to look for the name of the resource to load

To initialize hbase:

# First, copy hbase-site.xml into scala-playground folder.
:load inithbase.scala
val bytes = getFromHbase("tableName", "rowKey", "columnFamily", "columnName(Qualifier)")


To load spring application:

# First, define your spring file
val springfile = "applcationContext.txt"
:load initspring.scala
val myObj = context.getObject("myObj").asInstanceOf[MyClass]


To deserialize bytes into thrift object:

val myThriftObj = new MyThriftClass
deserializer.deserialize(myThriftObj, bytes)
# look at myThriftObj to see the content.
