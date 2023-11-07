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

    public void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.out.println(String.format("RECRUIT%50s%4s\n\n", this.getName(), this.getDepartmentName()));
        } catch (Exception e) {
            System.out.println("error: 화면을 제어할 수 없습니다.\n" + e);
        }
    }

    public void menu() {
        this.clearConsole();

        System.out.println("작업 유형을 선택해주세요.\n" +
                "1. 고객 관리\n" +
                "2. 공지글 관리\n" +
                "3. 고객 작성글 관리\n" +
                "4. 로그아웃");
    }
}
