echo off
set SERVICES_DIR=.\services

start "Config Server" cmd /k "cd %SERVICES_DIR%\config-server && gradlew bootRun"
start "Discovery" cmd /k "cd %SERVICES_DIR%\discovery && gradlew bootRun"
start "Gateway" cmd /k "cd %SERVICES_DIR%\gateway && gradlew bootRun"
start "Product" cmd /k "cd %SERVICES_DIR%\product && gradlew bootRun"
start "Product Third Party" cmd /k "cd %SERVICES_DIR%\product-third-party && gradlew bootRun"

::echo off
:: SERVICES_DIR=.\services

::start /b "Config Server" cmd /c "cd %SERVICES_DIR%\config-server && gradlew bootRun"
:: /b "Discovery" cmd /c "cd %SERVICES_DIR%\discovery && gradlew bootRun"
:: /b "Gateway" cmd /c "cd %SERVICES_DIR%\gateway && gradlew bootRun"
:: /b "Product" cmd /c "cd %SERVICES_DIR%\product && gradlew bootRun"
:: /b "Product Third Party" cmd /c "cd %SERVICES_DIR%\product-third-party && gradlew bootRun"

::echo All Services Executed

::pause > nul
::taskkill /im java.exe

