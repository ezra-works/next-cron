# next-cron

Simple javafx application for configuring crons and executing that runs as an executable jar designed for windows environment, however works with mac (linux should work but haven't got an env to test) as well. 

## Getting Started

The application can be run in 2 modes
1. GUI for configuring the crons
2. batch mode for executing the crons.

This javafx gui application provides simple way to setup a cron to run minute wise, hour wise, month, date with start and stop features.
The reason for writing this application was the windows scheduler wasn't helpful to configure crons to run at specific minutes alone. 
The windows scheduler will repeat the cron every 5 minutes and above and no option to run at specific time like cronA to run at 5, 12, 20, 22, 47, 58.

### Prerequisites

```
jdk : 8
maven : 3.2.1
ide : IntelliJ
Scene Builder : 8.5.0
```

you can download Scene Builder [here](https://gluonhq.com/products/scene-builder)

### Maven

just add the following dependencies 
```
        <dependency>
            <groupId>com.jfoenix</groupId>
            <artifactId>jfoenix</artifactId>
            <version>${jfoenix.version}</version>
        </dependency>
        <dependency>
            <groupId>org.controlsfx</groupId>
            <artifactId>controlsfx</artifactId>
            <version>${controlfx.version}</version>
        </dependency>
        <dependency>
            <groupId>com.oracle</groupId>
            <artifactId>javafx</artifactId>
            <version>2.2</version>
            <systemPath>${java.home}/lib/ext/jfxrt.jar</systemPath>
            <scope>system</scope>
        </dependency>
```

uses log4j2 for the logging 

```
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>${log4j-core.version}</version>
        </dependency>
```

uses fontawesomeFx for the icons

```
        <dependency>
            <groupId>de.jensd</groupId>
            <artifactId>fontawesomefx</artifactId
            <version>${fontawesomefx.version}</version>
        </dependency>
```

mvn clean package 

NOTE : You need to set JAVA_HOME environment variable to point to Java 1.8 directory.

NOTE : JFoenix requires Java 1.8u60 and above. refer [this](https://github.com/jfoenixadmin/JFoenix) for more info

NOTE : uses spring-boot plugin to create single executable jar. (one-jar, uber-jar does not help due to log4j2 issue)

## Authors

* **Ezra Rajkumar** - [Ezra](https://github.com/ezra-moses)


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## This project uses

* [Jfoneix](https://github.com/jfoenixadmin/JFoenix) 
* [fontawesomefx](https://www.jensd.de/wordpress/?p=2588)
