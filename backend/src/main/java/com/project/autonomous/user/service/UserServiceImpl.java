package com.project.autonomous.user.service;

import com.project.autonomous.common.exception.CustomException;
import com.project.autonomous.common.exception.ErrorCode;
import com.project.autonomous.jwt.util.SecurityUtil;
import com.project.autonomous.picture.entity.Picture;
import com.project.autonomous.picture.repository.PictureRepository;
import com.project.autonomous.picture.service.DBFileStorageService;
import com.project.autonomous.team.entity.Team;
import com.project.autonomous.team.repository.SportCategoryRepository;
import com.project.autonomous.user.dto.request.CheckPasswordReq;
import com.project.autonomous.user.dto.request.InterestReq;
import com.project.autonomous.user.dto.request.UserModifyReq;
import com.project.autonomous.user.dto.response.MyInfoRes;
import com.project.autonomous.user.dto.response.UserProfileRes;
import com.project.autonomous.user.dto.response.UserTeamListRes;
import com.project.autonomous.user.entity.User;
import com.project.autonomous.user.repository.UserInterestRepository;
import com.project.autonomous.user.repository.UserRepository;
import com.project.autonomous.user.repository.UserRepositorySupport;
import com.project.autonomous.user.repository.UserTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRepositorySupport userRepositorySupport;

    @Autowired
    UserTeamRepository userTeamRepository;

    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    SportCategoryRepository sportCategoryRepository;

    @Autowired
    UserInterestRepository userInterestRepository;
    private final PasswordEncoder passwordEncoder;
    private final DBFileStorageService dbFileStorageService;

    // 비밀번호 확인
    public boolean checkPassword(CheckPasswordReq checkPasswordReq) {
        String currentPass = userRepository.findById(SecurityUtil.getCurrentMemberId())
            .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND)).getPassword();

        return passwordEncoder.matches(checkPasswordReq.getPassword(), currentPass);
    }

    // 비밀번호 변경
    @Transactional
    public void changePassword(CheckPasswordReq checkPasswordReq) {
        User user = userRepository.findById(SecurityUtil.getCurrentMemberId())
            .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        user.changePassword(checkPasswordReq.encodePassword(passwordEncoder));
    }

    // 회원 수정
    @Transactional
    public MyInfoRes modifyUser(UserModifyReq userModifyReq) {
        User user = findMember(SecurityUtil.getCurrentMemberId());

        Picture picture;
        if (userModifyReq.getFile() == null) picture = null;
        else picture = dbFileStorageService.storeFile(userModifyReq.getFile());

        user.update(userModifyReq, picture);
        return MyInfoRes.from(user);
    }

    // 나의 팀 조회 (무한 스크롤)
    public Slice<UserTeamListRes> getMyTeams(Pageable pageable) {
        Slice<Team> teams = userTeamRepository.findTeamByUser(findMember(SecurityUtil.getCurrentMemberId()), pageable);
        return teams.map(p -> UserTeamListRes.from(p));
    }

    // 나의 개인 정보 조회
    public MyInfoRes getMyInfo() {
        return MyInfoRes.from(findMember(SecurityUtil.getCurrentMemberId()));
    }

    @Override
    public User deleteUser(Long userId) {
        return null;
    }

    @Override
    public UserProfileRes getUserProfile(Long userId) {
        User user = userRepository.findById(userId).get();

        UserProfileRes res = new UserProfileRes();
        res.setEmail(user.getEmail());
        res.setName(user.getName());
        res.setBirth(user.getBirth());
        res.setPhone(user.getPhone());
        res.setCity(user.getCity().toString());

        return res;
    }

    @Override
    public User getUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail).get();

        if (user == null) {
            return null;
        }
        return user;
    }

    public void updateInterest(InterestReq interestReq) {
        long userId = SecurityUtil.getCurrentMemberId();

        for (String name : interestReq.getSportCategory()) {
            long sportCategoryId = sportCategoryRepository.findByName(name).get().getId();
//            UserInterest userInterest = new UserInterest();
//            userInterest.setSportCategoryId(sportCategoryId);
//            userInterest.setUserId(userId);

//            userInterestRepository.save(userInterest);
        }

        return;
    }

    public User findMember(long userId) {
        User user = userRepository.findById(SecurityUtil.getCurrentMemberId())
            .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
//        if(user.isDeleted()) throw new CustomException(ErrorCode.DELETED_USER);
        return user;
    }
}