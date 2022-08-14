cd ..
echo "Deleting old jar files"
del .\build\libs\SmartHomeSimulator.jar
del .\docker-backend\SmartHomeSimulator.jar

echo "-- LINTING --"
echo "ktlint Format"
call .\gradlew ktlintFormat

echo "ng lint with fixing"
cd .\src\main\kotlin\org\smart\home\simulator\frontend
call ng lint --fix
cd ..\..\..\..\..\..\..\..\

echo "-- BUILDING JAR --"
echo "Gradle build with tests
call .\gradlew build
echo "Copying jar file"
copy .\build\libs\SmartHomeSimulator.jar .\docker-backend\SmartHomeSimulator.jar

echo "-- DEPLOYMENT --"
cd .\docker-backend
echo "Docker compose"
call docker-compose build
echo "-- FINISHED --"
