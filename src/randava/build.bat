@echo off
setlocal

REM === CONFIGURAÇÕES ===
set JAVA_FX_LIB=lib
set SRC_DIR=src
set OUT_DIR=out
set MAIN_CLASS=randava.RandavaFrontFX
set JAR_NAME=RandavaFrontFX.jar
set EXE_NAME=Randava
set ICON=icon.ico

REM === LIMPAR BUILD ANTIGA ===
if exist %OUT_DIR% rmdir /s /q %OUT_DIR%
mkdir %OUT_DIR%

echo.
echo === COMPILANDO CÓDIGO ===
javac --module-path %JAVA_FX_LIB% --add-modules javafx.controls,javafx.fxml -d %OUT_DIR% %SRC_DIR%\randava\*.java
if %ERRORLEVEL% neq 0 (
    echo ERRO: Falha na compilação.
    pause
    exit /b
)

echo.
echo === CRIANDO ARQUIVO JAR ===
echo Main-Class: %MAIN_CLASS% > manifest.txt
jar cfm %JAR_NAME% manifest.txt -C %OUT_DIR% .

echo.
echo === CRIANDO EXECUTÁVEL .EXE COM JPACKAGE ===
jpackage ^
  --type exe ^
  --input . ^
  --dest dist ^
  --name %EXE_NAME% ^
  --main-jar %JAR_NAME% ^
  --main-class %MAIN_CLASS% ^
  --module-path %JAVA_FX_LIB% ^
  --add-modules javafx.controls,javafx.fxml ^
  --icon %ICON% ^
  --win-console

if %ERRORLEVEL% neq 0 (
    echo ERRO: jpackage falhou.
    pause
    exit /b
)

echo.
echo === ASSINATURA DIGITAL (opcional) ===
REM REMOVA os 'REM' abaixo se quiser assinar automaticamente o .exe
REM signtool sign /f MeuCertificado.pfx /p senha123 ^
REM   /tr http://timestamp.digicert.com /td sha256 /fd sha256 ^
REM   dist\%EXE_NAME%\%EXE_NAME%.exe

echo.
echo === EXECUTANDO O PROGRAMA ===
dist\%EXE_NAME%\%EXE_NAME%.exe

pause
