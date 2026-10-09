```bat
@echo off
cd /d "%~dp0"

echo ==============================
echo 1. Nettoyage du dossier build
echo ==============================
if exist build rmdir /s /q build
mkdir build\WEB-INF\classes
mkdir build\WEB-INF\lib

echo ==============================
echo 2. Compilation des fichiers Java
echo ==============================
javac -cp "lib/*;E:\download\Nouveau dossier\apache-tomcat-9.0.122\lib\servlet-api.jar" -d build\WEB-INF\classes src\model\*.java src\util\*.java src\dao\*.java src\client\*.java src\controller\*.java

if errorlevel 1 (
    echo ERREUR : la compilation a echoue.
    pause
    exit /b 1
)

echo ==============================
echo 3. Copie des fichiers web
echo ==============================
xcopy web build /E /I /Y

echo ==============================
echo 4. Copie des bibliotheques
echo ==============================
copy /Y lib\*.jar build\WEB-INF\lib\

echo ==============================
echo 5. Creation du fichier WAR
echo ==============================
jar -cvf monprojet.war -C build .

if errorlevel 1 (
    echo ERREUR : creation du WAR echouee.
    pause
    exit /b 1
)

echo ==============================
echo 6. Deploiement dans Tomcat
echo ==============================
copy /Y monprojet.war "E:\download\Nouveau dossier\apache-tomcat-9.0.122\webapps\"

echo BUILD TERMINE !
pause
```