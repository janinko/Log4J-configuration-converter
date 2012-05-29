DIRNAME=`dirname $0`
TESTDIR=`mktemp -d`
JARFILE=log4jcc-0.6.jar

TESTFILESDIR=$DIRNAME/examples/testExamples/

cp $TESTFILESDIR* $TESTDIR

ARGS="-v"

for a in $TESTFILESDIR*.properties; do
	java -jar $JARFILE -i $a -o $TESTDIR/`basename $a`.properties -T properties $ARGS
done

for a in $TESTFILESDIR*.properties; do
	java -jar $JARFILE -i $a -o $TESTDIR/`basename $a`.xml -T xml $ARGS
done

for a in $TESTFILESDIR*.xml; do
	java -jar $JARFILE -i $a -o $TESTDIR/`basename $a`.xml -T xml $ARGS
done

for a in $TESTFILESDIR*.xml; do
	java -jar $JARFILE -i $a -o $TESTDIR/`basename $a`.properties -T properties $ARGS
done

for a in $TESTFILESDIR*.xml; do
	java -jar $JARFILE -i $TESTDIR/`basename $a`.properties -o $TESTDIR/`basename $a`.properties.xml -T xml $ARGS
done

for a in $TESTFILESDIR*.properties; do
	java -jar $JARFILE -i $TESTDIR/`basename $a`.xml -o $TESTDIR/`basename $a`.xml.properties -T properties $ARGS
done


cd $TESTDIR

for a in *.properties; do
	sort $a | sponge $a;
done

for a in *[0-9].properties; do
	cat $a | sed 's/\\*:/\\:/g;s/, */, /g;s/ *= */=/' | sponge $a; #WA filter for not perfect input properties
done

for a in *.properties; do
	cat $a | grep -vE "^(\s*$|#)" | sed 's/=$//' | sponge $a; #filter for removing comments, blank lines, and WA for not perfect input properties
done

echo; echo; echo; echo;

for a in *[0-9].properties; do
	diff $a $a.properties -U0 | grep "^[+-]"
done
	
for a in *[0-9].properties; do
	diff $a $a.xml.properties -U0 | grep "^[+-]"
done
	

