
find lib -name \*.jar -exec unzip -v {} \; | grep -oh --color=never '[^[:space:]]*class$' | tr / . | sed -E 's/^(.*).class$/import \1/' > /tmp/imports.scala
find lib -name \*.jar -exec unzip -v {} \; | grep -oh --color=never '[^[:space:]]*xml$' | sort > /tmp/xmls.txt

sublime /tmp/xmls.txt
sublime /tmp/imports.scala

LIB_PATH=`find lib | tr "\n" :`

# run: ":load /tmp/imports.scala" to load the imports
# scala -i /tmp/imports.scala -cp $LIB_PATH   # use this only when the library is small
#scala -cp $LIB_PATH
scala -cp .:$LIB_PATH
