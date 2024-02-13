package com.gml.semi_project.Service;

import com.gml.semi_project.DTO.UserJoinRequest;
import com.gml.semi_project.Entity.User;
import com.gml.semi_project.Enum.UserRole;
import com.gml.semi_project.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final LikeREpository likeRepository;
    private final CommentRepository commentRepository;
    private final BCryptPasswordEncoder encoder;

    public BindingResult joinValid(UserJoinRequest req, BindingResult bindingResult)
    {
        if(req.getMemberId().isEmpty()){
            bindingResult.addError(new FieldError("req", "memberId", "아이디를 입력해주세요."));
        }
        else if(req.getMemberId().length()>10){
            bindingResult.addError(new FieldError("req", "memberId", "아이디는 10자 이내입니다."));
        }
        else if (userRepository.existsByMoginId(req.getLoginId())) {
            bindingResult.addError(new FieldError("req", "memberId", "아이디가 중복됩니다."));
        }

        if (!req.getPassword().equals(req.getPasswordCheck())) {
            bindingResult.addError(new FieldError("req", "passwordCheck", "비밀번호가 일치하지 않습니다."));
        }

        if (req.getNickname().isEmpty()) {
            bindingResult.addError(new FieldError("req", "nickname", "닉네임이 비어있습니다."));
        } else if (req.getNickname().length() > 10) {
            bindingResult.addError(new FieldError("req", "nickname", "닉네임이 10자가 넘습니다."));
        } else if (userRepository.existsByNickname(req.getNickname())) {
            bindingResult.addError(new FieldError("req", "nickname", "닉네임이 중복됩니다."));
        }

        return bindingResult;
    }
    @Transactional
    public void edit(UserDto dto, String memberId) {
        User loginUser = userRepository.findByMemberId(memberId).get();

        if (dto.getNewPassword().equals("")) {
            loginUser.edit(loginUser.getPassword(), dto.getNickname());
        } else {
            loginUser.edit(encoder.encode(dto.getNewPassword()), dto.getNickname());
        }
    }

    @Transactional
    public Boolean delete(String loginId, String nowPassword) {
        User loginUser = userRepository.findByLoginId(loginId).get();

        if (encoder.matches(nowPassword, loginUser.getPassword())) {
            List<Like> likes = likeRepository.findAllByUserLoginId(loginId);
            for (Like like : likes) {
                like.getBoard().likeChange( like.getBoard().getLikeCnt() - 1 );
            }

            List<Comment> comments = commentRepository.findAllByUserLoginId(loginId);
            for (Comment comment : comments) {
                comment.getBoard().commentChange( comment.getBoard().getCommentCnt() - 1 );
            }

            userRepository.delete(loginUser);
            return true;
        } else {
            return false;
        }
    }

    public Page<User> findAllByNickname(String keyword, PageRequest pageRequest) {
        return userRepository.findAllByNicknameContains(keyword, pageRequest);
    }

    @Transactional
    public void changeRole(Long userId) {
        User user = userRepository.findById(userId).get();
        user.changeRole();
    }

    public UserCntDto getUserCnt() {
        return UserCntDto.builder()
                .totalUserCnt(userRepository.count())
                .totalAdminCnt(userRepository.countAllByUserRole(UserRole.ADMIN))
                .totalNewbieCnt(userRepository.countAllByUserRole(UserRole.NEWBIE))
                .totalVIPCnt(userRepository.countAllByUserRole(UserRole.VIP))
                .totalBlacklistCnt(userRepository.countAllByUserRole(UserRole.BLACKLIST))
                .build();
    }
}
