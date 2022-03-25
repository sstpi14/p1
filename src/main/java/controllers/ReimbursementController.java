package controllers;

import io.javalin.http.Context;
import models.JsonResponse;
import models.Reimbursement;
import services.ReimbursementService;

import java.util.List;
import java.util.Objects;

public class ReimbursementController {
    private ReimbursementService reimbursementService;

    public ReimbursementController() {
        this.reimbursementService = new ReimbursementService();
    }

    public ReimbursementController(ReimbursementService reimbursementService) {
        this.reimbursementService = reimbursementService;
    }

    //DISPLAYING REIMBURSEMENTS//
    //displaying all reimbursements for specific employee
    public void displayAllReimbursementsForEmployee(Context context) {
        Integer userId = Integer.parseInt(context.pathParam("userId"));

        List<Reimbursement> reimbursements = reimbursementService.getAllReimbByUserId(userId);
        context.json(new JsonResponse(true, "listing all reimbursement for user" + userId, reimbursements));
    }

    public void displayAllReimbursementsForAllEmployeesByStatus(Context context) {
        int statusId = Integer.parseInt(context.pathParam("statusId"));
        List<Reimbursement> reimbursements = reimbursementService.getAllReimbByStatusId(statusId);

        if (statusId == 1) {
            context.json(new JsonResponse(true, "Listing all pending reimbursements", reimbursements));
        }
        if (statusId == 2) {
            context.json(new JsonResponse(true, "Listing all approved reimbursements:", reimbursements));
        }
        if (statusId == 3) {
            context.json(new JsonResponse(true, "Listing all denied reimbursements:", reimbursements));
        }
    }

    public void displayAllReimbursements(Context context) {
        List<Reimbursement> reimbursements = reimbursementService.getAllReimbursements();

        context.json(new JsonResponse(true, "Listing all reimbursements", reimbursements));
    }
    public void displayReimbursementByReimbId(Context context) {
        int reimbId = Integer.parseInt(context.pathParam("reimbId"));
        List<Reimbursement> reimbursements = reimbursementService.getAllReimbByReimbId(reimbId);

        context.json(new JsonResponse(true, "Listing all reimbursements with reimbId" + reimbId, reimbursements));
    }

    public void createReimbursement(Context context){
        Reimbursement reimbursement = context.bodyAsClass(Reimbursement.class);
        reimbursementService.createReimbursement(reimbursement);

        context.json(new JsonResponse(true, "Reimbursement Submitted", reimbursement));
    }

    public void updateReimbursement(Context context) {
        Reimbursement reimbursement = context.bodyAsClass(Reimbursement.class);
        reimbursementService.updateReimbursement((reimbursement));

        context.json(new JsonResponse(true, "Reimbursement Updated", reimbursement));
    }
}
