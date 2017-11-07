package net.hailm.recyclerviewlab11.view;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import net.hailm.recyclerviewlab11.R;
import net.hailm.recyclerviewlab11.adapter.StoriesAdapter;
import net.hailm.recyclerviewlab11.model.Story;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StoriesListActivity extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout drawerLayout;
    private ImageView imgMenu;
    private List<Story> stories;
    private RecyclerView rcvStories;
    private StoriesAdapter storiesAdapter;
    private Random rd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        creatListStories();
        initializeComponents();
        registerListener();
    }


    private void initializeComponents() {
        imgMenu = findViewById(R.id.img_menu);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        rcvStories = findViewById(R.id.rcv_stories);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rcvStories.setLayoutManager(llm);

        storiesAdapter = new StoriesAdapter(stories, this);
        rcvStories.setAdapter(storiesAdapter);
    }

    private void registerListener() {
        imgMenu.setOnClickListener(this);
    }

    private void creatListStories() {
        stories = new ArrayList<>();
        rd = new Random();

        stories.add(new Story("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBlT5gy0VZTpNIfhlTIZnO0Cyoat4DN5n9jObhJHizcCOVz3dZlw",
                "Truyen 1",
                "Yên Mang",
                "Ngôn tình",
                "Đang cập nhật",
                rd.nextInt(200),
                "2017/10/25 22:59:59",
                "2017/10/28 22:59:59",
                "Nam Thần Ở Phòng Bên Cạnh là một trong những truyện ngôn tình hay nói về phòng bên cạnh chuyển tới một mỹ nam kiêu ngạo, tự đại, IQ=max EQ=0. Ngay từ ngày đầu tiên gặp mặt cô đã bị anh đưa vào đồn cảnh sát nên Tô Song Song quyết định thù này không báo không phải nữ tử, không ngờ kế hoạch trả thù rất thuận lợi, thế nhưng anh lại nói \"Cô làm hỏng hôn sự của tôi nên cô phải dùng chính mình để đền.\"Thời điểm Tô Song Song bị cảnh sát mang đi, ý nghĩ đầu tiên hiện lên trong đầu cô chính là, nếu như lúc mở cửa ra không ngẩng đầu lên thì tốt rồi, chỉ tiếc rằng bây giờ mới nhớ tới đã quá muộn. Trên đời này không bán loại thuốc hối hận.\n" +
                        "\n" +
                        "Tô Song Song quay đầu lại cố gắng trừng mắt một cách hung tợn nhất liếc nhìn người đang đứng trước cửa phòng bên cạnh, nét mặt tên đầu sỏ của mọi chuyện vừa xảy ra không có lấy một chút biểu cảm. Cô chỉ có thể oán giận rống lên một câu: \"Tốt nhất anh đừng chuyển nhà đi. Tôi tuyệt đối sẽ không bỏ qua cho anh!\"\n" +
                        "\n" +
                        "Trong lòng cô vô cùng bi phẫn nghĩ: \"Anh ta tuy rằng bề ngoài có phần đẹp trai, nhưng trong tiết trời cuối thu rồi còn khỏa thân, chỉ quấn mỗi một chiếc khăn tắm, còn không biết xấu hổ cố tình để lộ ra cơ bụng tám múi, vừa thấy đã biết không phải là người tốt đẹp gì.\""));
        stories.add(new Story("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBlT5gy0VZTpNIfhlTIZnO0Cyoat4DN5n9jObhJHizcCOVz3dZlw",
                "Truyen 2",
                "Mèo Tứ Nhi",
                "Ngôn tình",
                "Đang cập nhật",
                rd.nextInt(200),
                "2017/10/25 22:59:59",
                "2017/10/28 22:59:59",
                "Nam Thần Ở Phòng Bên Cạnh là một trong những truyện ngôn tình hay nói về phòng bên cạnh chuyển tới một mỹ nam kiêu ngạo, tự đại, IQ=max EQ=0. Ngay từ ngày đầu tiên gặp mặt cô đã bị anh đưa vào đồn cảnh sát nên Tô Song Song quyết định thù này không báo không phải nữ tử, không ngờ kế hoạch trả thù rất thuận lợi, thế nhưng anh lại nói \"Cô làm hỏng hôn sự của tôi nên cô phải dùng chính mình để đền.\"Thời điểm Tô Song Song bị cảnh sát mang đi, ý nghĩ đầu tiên hiện lên trong đầu cô chính là, nếu như lúc mở cửa ra không ngẩng đầu lên thì tốt rồi, chỉ tiếc rằng bây giờ mới nhớ tới đã quá muộn. Trên đời này không bán loại thuốc hối hận.\n" +
                        "\n" +
                        "Tô Song Song quay đầu lại cố gắng trừng mắt một cách hung tợn nhất liếc nhìn người đang đứng trước cửa phòng bên cạnh, nét mặt tên đầu sỏ của mọi chuyện vừa xảy ra không có lấy một chút biểu cảm. Cô chỉ có thể oán giận rống lên một câu: \"Tốt nhất anh đừng chuyển nhà đi. Tôi tuyệt đối sẽ không bỏ qua cho anh!\"\n" +
                        "\n" +
                        "Trong lòng cô vô cùng bi phẫn nghĩ: \"Anh ta tuy rằng bề ngoài có phần đẹp trai, nhưng trong tiết trời cuối thu rồi còn khỏa thân, chỉ quấn mỗi một chiếc khăn tắm, còn không biết xấu hổ cố tình để lộ ra cơ bụng tám múi, vừa thấy đã biết không phải là người tốt đẹp gì.\""));
        stories.add(new Story("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBlT5gy0VZTpNIfhlTIZnO0Cyoat4DN5n9jObhJHizcCOVz3dZlw",
                "Truyen 3",
                "Mèo Tứ Nhi",
                "Ngôn tình",
                "Đang cập nhật",
                rd.nextInt(200),
                "2017/10/25 22:59:59",
                "2017/10/28 22:59:59",
                "Nam Thần Ở Phòng Bên Cạnh là một trong những truyện ngôn tình hay nói về phòng bên cạnh chuyển tới một mỹ nam kiêu ngạo, tự đại, IQ=max EQ=0. Ngay từ ngày đầu tiên gặp mặt cô đã bị anh đưa vào đồn cảnh sát nên Tô Song Song quyết định thù này không báo không phải nữ tử, không ngờ kế hoạch trả thù rất thuận lợi, thế nhưng anh lại nói \"Cô làm hỏng hôn sự của tôi nên cô phải dùng chính mình để đền.\"Thời điểm Tô Song Song bị cảnh sát mang đi, ý nghĩ đầu tiên hiện lên trong đầu cô chính là, nếu như lúc mở cửa ra không ngẩng đầu lên thì tốt rồi, chỉ tiếc rằng bây giờ mới nhớ tới đã quá muộn. Trên đời này không bán loại thuốc hối hận.\n" +
                        "\n" +
                        "Tô Song Song quay đầu lại cố gắng trừng mắt một cách hung tợn nhất liếc nhìn người đang đứng trước cửa phòng bên cạnh, nét mặt tên đầu sỏ của mọi chuyện vừa xảy ra không có lấy một chút biểu cảm. Cô chỉ có thể oán giận rống lên một câu: \"Tốt nhất anh đừng chuyển nhà đi. Tôi tuyệt đối sẽ không bỏ qua cho anh!\"\n" +
                        "\n" +
                        "Trong lòng cô vô cùng bi phẫn nghĩ: \"Anh ta tuy rằng bề ngoài có phần đẹp trai, nhưng trong tiết trời cuối thu rồi còn khỏa thân, chỉ quấn mỗi một chiếc khăn tắm, còn không biết xấu hổ cố tình để lộ ra cơ bụng tám múi, vừa thấy đã biết không phải là người tốt đẹp gì.\""));
        stories.add(new Story("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBlT5gy0VZTpNIfhlTIZnO0Cyoat4DN5n9jObhJHizcCOVz3dZlw",
                "Mẹ 17 Tuổi: Con Trai Thiên Tài Cha Phúc Hắc",
                "Hi Vũ Yên",
                "Ngôn tình",
                "Đang cập nhật",
                rd.nextInt(200),
                "2017/10/25 22:59:59",
                "2017/10/28 22:59:59",
                "Nam Thần Ở Phòng Bên Cạnh là một trong những truyện ngôn tình hay nói về phòng bên cạnh chuyển tới một mỹ nam kiêu ngạo, tự đại, IQ=max EQ=0. Ngay từ ngày đầu tiên gặp mặt cô đã bị anh đưa vào đồn cảnh sát nên Tô Song Song quyết định thù này không báo không phải nữ tử, không ngờ kế hoạch trả thù rất thuận lợi, thế nhưng anh lại nói \"Cô làm hỏng hôn sự của tôi nên cô phải dùng chính mình để đền.\"Thời điểm Tô Song Song bị cảnh sát mang đi, ý nghĩ đầu tiên hiện lên trong đầu cô chính là, nếu như lúc mở cửa ra không ngẩng đầu lên thì tốt rồi, chỉ tiếc rằng bây giờ mới nhớ tới đã quá muộn. Trên đời này không bán loại thuốc hối hận.\n" +
                        "\n" +
                        "Tô Song Song quay đầu lại cố gắng trừng mắt một cách hung tợn nhất liếc nhìn người đang đứng trước cửa phòng bên cạnh, nét mặt tên đầu sỏ của mọi chuyện vừa xảy ra không có lấy một chút biểu cảm. Cô chỉ có thể oán giận rống lên một câu: \"Tốt nhất anh đừng chuyển nhà đi. Tôi tuyệt đối sẽ không bỏ qua cho anh!\"\n" +
                        "\n" +
                        "Trong lòng cô vô cùng bi phẫn nghĩ: \"Anh ta tuy rằng bề ngoài có phần đẹp trai, nhưng trong tiết trời cuối thu rồi còn khỏa thân, chỉ quấn mỗi một chiếc khăn tắm, còn không biết xấu hổ cố tình để lộ ra cơ bụng tám múi, vừa thấy đã biết không phải là người tốt đẹp gì.\""));
        stories.add(new Story("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBlT5gy0VZTpNIfhlTIZnO0Cyoat4DN5n9jObhJHizcCOVz3dZlw",
                "Cô Vợ Trẻ Con Của Lăng Thiếu Bá Đạo",
                "Yên Mang",
                "Ngôn tình",
                "Đang cập nhật",
                rd.nextInt(200),
                "2017/10/25 22:59:59",
                "2017/10/28 22:59:59",
                "Nam Thần Ở Phòng Bên Cạnh là một trong những truyện ngôn tình hay nói về phòng bên cạnh chuyển tới một mỹ nam kiêu ngạo, tự đại, IQ=max EQ=0. Ngay từ ngày đầu tiên gặp mặt cô đã bị anh đưa vào đồn cảnh sát nên Tô Song Song quyết định thù này không báo không phải nữ tử, không ngờ kế hoạch trả thù rất thuận lợi, thế nhưng anh lại nói \"Cô làm hỏng hôn sự của tôi nên cô phải dùng chính mình để đền.\"Thời điểm Tô Song Song bị cảnh sát mang đi, ý nghĩ đầu tiên hiện lên trong đầu cô chính là, nếu như lúc mở cửa ra không ngẩng đầu lên thì tốt rồi, chỉ tiếc rằng bây giờ mới nhớ tới đã quá muộn. Trên đời này không bán loại thuốc hối hận.\n" +
                        "\n" +
                        "Tô Song Song quay đầu lại cố gắng trừng mắt một cách hung tợn nhất liếc nhìn người đang đứng trước cửa phòng bên cạnh, nét mặt tên đầu sỏ của mọi chuyện vừa xảy ra không có lấy một chút biểu cảm. Cô chỉ có thể oán giận rống lên một câu: \"Tốt nhất anh đừng chuyển nhà đi. Tôi tuyệt đối sẽ không bỏ qua cho anh!\"\n" +
                        "\n" +
                        "Trong lòng cô vô cùng bi phẫn nghĩ: \"Anh ta tuy rằng bề ngoài có phần đẹp trai, nhưng trong tiết trời cuối thu rồi còn khỏa thân, chỉ quấn mỗi một chiếc khăn tắm, còn không biết xấu hổ cố tình để lộ ra cơ bụng tám múi, vừa thấy đã biết không phải là người tốt đẹp gì.\""));
        stories.add(new Story("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBlT5gy0VZTpNIfhlTIZnO0Cyoat4DN5n9jObhJHizcCOVz3dZlw",
                "Nam Thần Ở Phòng Bên Cạnh",
                "Yên Mang",
                "Ngôn tình",
                "Đang cập nhật",
                rd.nextInt(200),
                "2017/10/25 22:59:59",
                "2017/10/28 22:59:59",
                "Nam Thần Ở Phòng Bên Cạnh là một trong những truyện ngôn tình hay nói về phòng bên cạnh chuyển tới một mỹ nam kiêu ngạo, tự đại, IQ=max EQ=0. Ngay từ ngày đầu tiên gặp mặt cô đã bị anh đưa vào đồn cảnh sát nên Tô Song Song quyết định thù này không báo không phải nữ tử, không ngờ kế hoạch trả thù rất thuận lợi, thế nhưng anh lại nói \"Cô làm hỏng hôn sự của tôi nên cô phải dùng chính mình để đền.\"Thời điểm Tô Song Song bị cảnh sát mang đi, ý nghĩ đầu tiên hiện lên trong đầu cô chính là, nếu như lúc mở cửa ra không ngẩng đầu lên thì tốt rồi, chỉ tiếc rằng bây giờ mới nhớ tới đã quá muộn. Trên đời này không bán loại thuốc hối hận.\n" +
                        "\n" +
                        "Tô Song Song quay đầu lại cố gắng trừng mắt một cách hung tợn nhất liếc nhìn người đang đứng trước cửa phòng bên cạnh, nét mặt tên đầu sỏ của mọi chuyện vừa xảy ra không có lấy một chút biểu cảm. Cô chỉ có thể oán giận rống lên một câu: \"Tốt nhất anh đừng chuyển nhà đi. Tôi tuyệt đối sẽ không bỏ qua cho anh!\"\n" +
                        "\n" +
                        "Trong lòng cô vô cùng bi phẫn nghĩ: \"Anh ta tuy rằng bề ngoài có phần đẹp trai, nhưng trong tiết trời cuối thu rồi còn khỏa thân, chỉ quấn mỗi một chiếc khăn tắm, còn không biết xấu hổ cố tình để lộ ra cơ bụng tám múi, vừa thấy đã biết không phải là người tốt đẹp gì.\""));
        stories.add(new Story("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBlT5gy0VZTpNIfhlTIZnO0Cyoat4DN5n9jObhJHizcCOVz3dZlw",
                "Tổng Giám Đốc Anh Thật Là Hư",
                "Hi Vũ Yên",
                "Ngôn tình",
                "Đang cập nhật",
                rd.nextInt(200),
                "2017/10/25 22:59:59",
                "2017/10/28 22:59:59",
                "Nam Thần Ở Phòng Bên Cạnh là một trong những truyện ngôn tình hay nói về phòng bên cạnh chuyển tới một mỹ nam kiêu ngạo, tự đại, IQ=max EQ=0. Ngay từ ngày đầu tiên gặp mặt cô đã bị anh đưa vào đồn cảnh sát nên Tô Song Song quyết định thù này không báo không phải nữ tử, không ngờ kế hoạch trả thù rất thuận lợi, thế nhưng anh lại nói \"Cô làm hỏng hôn sự của tôi nên cô phải dùng chính mình để đền.\"Thời điểm Tô Song Song bị cảnh sát mang đi, ý nghĩ đầu tiên hiện lên trong đầu cô chính là, nếu như lúc mở cửa ra không ngẩng đầu lên thì tốt rồi, chỉ tiếc rằng bây giờ mới nhớ tới đã quá muộn. Trên đời này không bán loại thuốc hối hận.\n" +
                        "\n" +
                        "Tô Song Song quay đầu lại cố gắng trừng mắt một cách hung tợn nhất liếc nhìn người đang đứng trước cửa phòng bên cạnh, nét mặt tên đầu sỏ của mọi chuyện vừa xảy ra không có lấy một chút biểu cảm. Cô chỉ có thể oán giận rống lên một câu: \"Tốt nhất anh đừng chuyển nhà đi. Tôi tuyệt đối sẽ không bỏ qua cho anh!\"\n" +
                        "\n" +
                        "Trong lòng cô vô cùng bi phẫn nghĩ: \"Anh ta tuy rằng bề ngoài có phần đẹp trai, nhưng trong tiết trời cuối thu rồi còn khỏa thân, chỉ quấn mỗi một chiếc khăn tắm, còn không biết xấu hổ cố tình để lộ ra cơ bụng tám múi, vừa thấy đã biết không phải là người tốt đẹp gì.\""));
        stories.add(new Story("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBlT5gy0VZTpNIfhlTIZnO0Cyoat4DN5n9jObhJHizcCOVz3dZlw",
                "Cha Tổng Giám Đốc Quá Càn Rỡ",
                "Yên Mang",
                "Ngôn tình",
                "Đang cập nhật",
                rd.nextInt(200),
                "2017/10/25 22:59:59",
                "2017/10/28 22:59:59",
                "Nam Thần Ở Phòng Bên Cạnh là một trong những truyện ngôn tình hay nói về phòng bên cạnh chuyển tới một mỹ nam kiêu ngạo, tự đại, IQ=max EQ=0. Ngay từ ngày đầu tiên gặp mặt cô đã bị anh đưa vào đồn cảnh sát nên Tô Song Song quyết định thù này không báo không phải nữ tử, không ngờ kế hoạch trả thù rất thuận lợi, thế nhưng anh lại nói \"Cô làm hỏng hôn sự của tôi nên cô phải dùng chính mình để đền.\"Thời điểm Tô Song Song bị cảnh sát mang đi, ý nghĩ đầu tiên hiện lên trong đầu cô chính là, nếu như lúc mở cửa ra không ngẩng đầu lên thì tốt rồi, chỉ tiếc rằng bây giờ mới nhớ tới đã quá muộn. Trên đời này không bán loại thuốc hối hận.\n" +
                        "\n" +
                        "Tô Song Song quay đầu lại cố gắng trừng mắt một cách hung tợn nhất liếc nhìn người đang đứng trước cửa phòng bên cạnh, nét mặt tên đầu sỏ của mọi chuyện vừa xảy ra không có lấy một chút biểu cảm. Cô chỉ có thể oán giận rống lên một câu: \"Tốt nhất anh đừng chuyển nhà đi. Tôi tuyệt đối sẽ không bỏ qua cho anh!\"\n" +
                        "\n" +
                        "Trong lòng cô vô cùng bi phẫn nghĩ: \"Anh ta tuy rằng bề ngoài có phần đẹp trai, nhưng trong tiết trời cuối thu rồi còn khỏa thân, chỉ quấn mỗi một chiếc khăn tắm, còn không biết xấu hổ cố tình để lộ ra cơ bụng tám múi, vừa thấy đã biết không phải là người tốt đẹp gì.\""));

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_menu) {
            drawerLayout.openDrawer(Gravity.START);
        }
    }

}
