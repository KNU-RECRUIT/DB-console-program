package lab7;

public class Screen {
    private String name;
    private String departmentName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Screen(String name, String department_name) {
        this.name = name;
        this.departmentName = department_name;
    }

    public static void title() {
        // https://patorjk.com/software/taag/#p=display&f=Graffiti&t=RECRUIT
        System.out.println(
                "__________  ___________ _________   __________   ____ ___  .___  ___________\n" +
                "\\______   \\ \\_   _____/ \\_   ___ \\  \\______   \\ |    |   \\ |   | \\__    ___/\n" +
                " |       _/  |    __)_  /    \\  \\/   |       _/ |    |   / |   |   |    |   \n" +
                " |    |   \\  |        \\ \\     \\____  |    |   \\ |    |  /  |   |   |    |   \n" +
                " |____|_  / /_______  /  \\______  /  |____|_  / |______/   |___|   |____|   \n" +
                "        \\/          \\/          \\/          \\/                              \n" +
                "----------------------------------------------------------------------------\n");
    }

    public void resetConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.out.println(String.format("RECRUIT%61s%4s\n\n", this.getDepartmentName(), this.getName()));
        } catch (Exception e) {
            System.out.println("error: 화면을 제어할 수 없습니다.\n" + e);
        }
    }

    public void menu() {
        this.resetConsole();

        System.out.println("작업 유형을 선택해주세요.\n" +
                "1. 고객 관리\n" +
                "2. 공지글 관리\n" +
                "3. 고객 작성글 관리\n" +
                "4. 로그아웃");
    }
}
