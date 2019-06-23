# next-cron

Simple javafx cron application that runs as an executable jar designed for windows environment, however works with mac (linux should work but haven't got an env to test) as well. 

The application can be run in 2 modes
1. GUI for configuring the crons
2. batch mode for executing the crons.

This javafx gui application provides simple way to setup a cron to run minute wise, hour wise, month, date with start and stop features.
The reason for writing this application was the windows scheduler wasn't helpful to configure crons to run at specific minutes alone. 
The windows scheduler will repeat the cron every 5 minutes and above and no option to run at specific time like cronA to run at 5, 12, 20, 22, 47, 58. 
