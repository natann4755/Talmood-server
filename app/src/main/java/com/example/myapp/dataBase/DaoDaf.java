package com.example.myapp.dataBase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.model.Daf;
import com.example.model.DafLearned;
import com.example.model.Learning;
import com.example.model.StudyPlan;

import java.util.Collection;
import java.util.List;

@Dao
public interface DaoDaf {

    @Query("SELECT * FROM StudyPlan")
    List<StudyPlan> getAllStudyPlans();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStudyPlan(StudyPlan studyPlan);

    @Query("SELECT MAX(id) FROM StudyPlan")
    int getHighestStudyPlanId();

    @Query("DELETE FROM StudyPlan WHERE id = :StudyPlanID")
    void deleteStudyPlan(int StudyPlanID);

    @Transaction
    @Query("SELECT * FROM StudyPlan")
    List<Learning> getStudyPlanWithLearning();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDaf(DafLearned dafLearned);

    @Query("DELETE FROM DafLearned WHERE dafLearnedId = :dafLearnedId")
    void deleteDaf (int studyPlanId, int dafLearnedId);
//
//    private int dafLearnedId;
//    private int studyPlanID;
//    private String masechetName;
//    private int pageNumber;
//    private int chazara;
//    private int indexInListDafs;








    @Query("SELECT * FROM DafLearned")
    List<DafLearned> getAllDafLearned();





    @Query("SELECT * FROM Daf")
    List<Daf> getAllHistoricalLearning();

    @Query("SELECT MAX(id) FROM Daf")
    int getHighestId();

    @Query("SELECT * FROM Daf WHERE indexTypeOfStudy = :typeOfStudy")
    List<Daf> getAllLearning(int typeOfStudy);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllLearning(Collection<Daf> AllLearning);

    @Query("UPDATE Daf SET isLearning = :isLearning WHERE indexTypeOfStudy = :typeOfStudy and masechet = :masechet and pageNumber = :page")
    void updateIsLearning(boolean isLearning ,int typeOfStudy, String masechet , int page);

    @Query("UPDATE Daf SET chazara = :chazara WHERE indexTypeOfStudy = :typeOfStudy and masechet = :masechet and pageNumber = :page")
    void updateNumOfChazara(int chazara , int typeOfStudy, String masechet , int page);

    @Query("DELETE FROM Daf")
    void deleteAll();

    @Query("SELECT DISTINCT indexTypeOfStudy FROM Daf WHERE NOT indexTypeOfStudy = 0")
    List<Integer> getAllIndexTypeOfLeaning();

    @Query("DELETE FROM Daf WHERE indexTypeOfStudy = :typeOfLeaning and isLearning = 0")
    void deleteTypeOfLeaning(int typeOfLeaning);

    @Query("UPDATE Daf SET indexTypeOfStudy = 0 WHERE indexTypeOfStudy = :typeOfLeaning and isLearning = 1")
    void updateDeletedLeaning(int typeOfLeaning);

    @Query("UPDATE Daf SET indexTypeOfStudy = :typeOfLeaning -1 WHERE indexTypeOfStudy = :typeOfLeaning")
    void updateIndexTypeOfStudy(int typeOfLeaning);

}
