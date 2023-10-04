package frc.robot;

public class Constants {

    public static class DriveTrainConstants {

        public static final int kLeftLeaderMotorID = 1;

        public static final int kLeftFollower1MotorID = 2;
        public static final int kLeftFollower2MotorID = 3;

        public static final int kRightLeaderMotorID = 4;

        public static final int kRightFollower1MotorID = 5;
        public static final int kRightFollower2MotorID = 6;


        public static final int kDriveMotorSmartCurrentLimit = 20;
    }

    public static class OperatorConstants {
        public static final int programmingControllerPort = 0;
        public static final int leftJoystickPort = 1;
        public static final int rightJoystickPort = 2;
    }

    public static class ArmPoses{
        public static final int Tucked = 0;
        public static final int ScoreLow = 40;
        public static final int ScoreMid = 60;
        public static final int ScoreHigh = 115;
    }

    

}
