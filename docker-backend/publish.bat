cd ..
echo "Deleting old jar files"
del .\build\libs\OcppServer.jar
del .\docker-backend\OcppServer.jar

echo "-- LINTING --"
echo "ktlint Format"
call .\gradlew ktlintFormat

echo "ng lint with fixing"
cd .\src\main\kotlin\org\ocpp\server\frontend
call ng lint --fix
cd ..\..\..\..\..\..\..\

echo "-- BUILDING JAR --"
echo "Gradle build without tests. Run tests manually if required
call .\gradlew build -x test
echo "Copying jar file"
copy .\build\libs\OcppServer.jar .\docker-backend\OcppServer.jar

echo "-- DEPLOYMENT --"
cd .\docker-backend
echo "Docker compose"
call docker-compose build
echo "-- FINISHED --"
