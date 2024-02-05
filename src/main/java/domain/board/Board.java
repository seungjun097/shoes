package domain.board;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	private int board_key;
	private int member_key;
	private String board_title;
	private Date board_date;
	private String board_content;
	private int board_visitcount;
}
